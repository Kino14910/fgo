import json
import os
import re
import pandas as pd

# ================== 配置区 ==================
JSON_FILE = "./zhs/CardStrings.json"          # 你的 JSON 文件路径
CARDS_DIR = "../../../java/fgo/cards"         # Java 卡片根目录
OUTPUT_EXCEL = "cards_output.xlsx"
MOD_ID_PREFIX = "${modID}:"
# ===========================================

def parse_java_card(code: str, filename: str = "unknown"):
    data = {
        'cost': None,
        'type': '',
        'target': '',
        'rarity': '',
        'damage': None,
        'block': None,
        'magicNumber': None,
        'damageUpgrade': None,
        'blockUpgrade': None,
        'magicUpgrade': None,
        'costUpgrade': None,
    }

    # === 通用 super(...) 匹配（支持 5~7 参数，全限定名，负数费用）===
    super_pattern = (
        r'super\s*\(\s*'
        r'[^,]+'                      # ID (ignored)
        r'\s*,\s*'
        r'([+-]?\d+|\w+)'             # cost: 可以是 -2, 0, COST 等
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardType\.(\w+)'      # type
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardTarget\.(\w+)'    # target
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardRarity\.(\w+)'    # rarity
        r'(?:\s*,[^)]*)?'             # 可选的后续参数（color, cardPath等），全部忽略
        r'\s*\)'
    )

    match = re.search(super_pattern, code, re.DOTALL)
    if match:
        cost_str = match.group(1)
        data['type'] = match.group(2)
        data['target'] = match.group(3)
        data['rarity'] = match.group(4)

        # 处理费用：只接受整数（包括负数），否则留空
        if re.fullmatch(r'[+-]?\d+', cost_str):
            data['cost'] = int(cost_str)
    else:
        # 尝试匹配 AbsNoblePhantasmCard 格式（type, target, cost）
        alt_pattern = (
            r'super\s*\(\s*'
            r'[^,]+'
            r'\s*,\s*'
            r'(?:AbstractCard\.)?CardType\.(\w+)'
            r'\s*,\s*'
            r'(?:AbstractCard\.)?CardTarget\.(\w+)'
            r'\s*,\s*'
            r'([+-]?\d+|\w+)'
            r'\s*\)'
        )
        alt_match = re.search(alt_pattern, code)
        if alt_match:
            data['type'] = alt_match.group(1)
            data['target'] = alt_match.group(2)
            cost_str = alt_match.group(3)
            data['rarity'] = "SPECIAL"
            if re.fullmatch(r'[+-]?\d+', cost_str):
                data['cost'] = int(cost_str)
        else:
            print(f"⚠️ 仍无法识别 super 构造器 in {filename}.java")
            for line in code.splitlines():
                if 'super(' in line and not line.strip().startswith("//"):
                    print(f"   → {line.strip()}")
                    break

    # === 其他字段解析 ===
    dmg_match = re.search(r'setDamage\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if dmg_match:
        base, delta = int(dmg_match.group(1)), int(dmg_match.group(2))
        data['damage'] = base
        data['damageUpgrade'] = base + delta

    blk_match = re.search(r'setBlock\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if blk_match:
        base, delta = int(blk_match.group(1)), int(blk_match.group(2))
        data['block'] = base
        data['blockUpgrade'] = base + delta

    mag_match = re.search(r'setMagic\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if mag_match:
        base, delta = int(mag_match.group(1)), int(mag_match.group(2))
        data['magicNumber'] = base
        data['magicUpgrade'] = base + delta

    np_match = re.search(r'setNP\s*\(\s*(\d+)\s*\)', code)
    if np_match and data['magicNumber'] is None:
        val = int(np_match.group(1))
        data['magicNumber'] = val
        data['magicUpgrade'] = val

    cost_up_match = re.search(r'setCostUpgrade\s*\(\s*(\d+)\s*\)', code)
    if cost_up_match:
        data['costUpgrade'] = int(cost_up_match.group(1))

    return data

def clean_desc(s: str) -> str:
    if not s:
        return ""
    return s.replace("NL", "\n")

def main():
    # 1. 读取 JSON 并保留原始顺序
    with open(JSON_FILE, 'r', encoding='utf-8') as f:
        json_data = json.load(f)

    # 2. 扫描所有 Java 文件，建立映射
    java_card_info = {}
    for root, _, files in os.walk(CARDS_DIR):
        for file in files:
            if file.endswith(".java"):
                card_id = file[:-5]  # 去掉 .java
                java_path = os.path.join(root, file)
                try:
                    with open(java_path, 'r', encoding='utf-8') as f:
                        code = f.read()
                    java_info = parse_java_card(code, card_id)
                    java_card_info[card_id] = java_info
                except Exception as e:
                    print(f"❌ 读取失败 {java_path}: {e}")
                    continue

    # 3. 按 JSON 顺序构建行数据（关键：确保顺序一致）
    rows = []
    for full_key, json_info in json_data.items():
        # 提取 card_id（去掉前缀）
        if full_key.startswith(MOD_ID_PREFIX):
            card_id = full_key[len(MOD_ID_PREFIX):]
        else:
            card_id = full_key  # 无前缀的情况

        # 获取 Java 信息（如果存在）
        java_info = java_card_info.get(card_id, {
            'cost': None, 'type': '', 'target': '', 'rarity': '',
            'damage': None, 'block': None, 'magicNumber': None,
            'damageUpgrade': None, 'blockUpgrade': None, 'magicUpgrade': None,
            'costUpgrade': None
        })

        # 构建行数据
        row = {
            "ID": card_id,
            "牌名": json_info.get("NAME", ""),
            "稀有度": java_info['rarity'],
            "类型": java_info['type'],
            "目标": java_info['target'],
            "费用": java_info['cost'],
            "描述": clean_desc(json_info.get("DESCRIPTION", "")),
            "伤害值": java_info['damage'],
            "格挡值": java_info['block'],
            "特殊值": java_info['magicNumber'],
            "费用+": java_info['costUpgrade'],
            "强化版描述": clean_desc(json_info.get("UPGRADE_DESCRIPTION", "")),
            "伤害值+": java_info['damageUpgrade'],
            "格挡值+": java_info['blockUpgrade'],
            "特殊值+": java_info['magicUpgrade'],
        }
        rows.append(row)

    # 4. 输出 Excel
    df = pd.DataFrame(rows, columns=[
        "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
        "伤害值", "格挡值", "特殊值", "费用+", "强化版描述",
        "伤害值+", "格挡值+", "特殊值+"
    ])
    df.to_excel(OUTPUT_EXCEL, index=False)
    print(f"✅ 成功生成 Excel: {OUTPUT_EXCEL} （共 {len(rows)} 张卡）")
    print("✅ 顺序已按 JSON 文件中键的顺序排列")

if __name__ == "__main__":
    main()