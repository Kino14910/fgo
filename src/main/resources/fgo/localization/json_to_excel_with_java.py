import json
import os
import re
import pandas as pd

# ================== 配置区 ==================
JSON_FILE = "./zhs/CardStrings.json"          # JSON 文件路径
CARDS_DIR = "../../../java/fgo/cards"         # Java 卡片根目录
OUTPUT_EXCEL_NORMAL = "cards_normal.xlsx"     # 普通卡输出文件
OUTPUT_EXCEL_NOBLE = "cards_noble.xlsx"       # 宝具卡输出文件
MOD_ID_PREFIX = "${modID}:"
# ===========================================

def parse_java_card(code: str, filename: str = "unknown"):
    """
    从 Java 代码中解析卡片信息
    """
    # 初始化数据字典，包含所有需要提取的字段
    data = {
        'cost': None,           # 费用
        'type': '',             # 类型（ATTACK/SKILL/POWER）
        'target': '',           # 目标（ENEMY/SELF/ALL_ENEMY等）
        'rarity': '',           # 稀有度（COMMON/RARE/SPECIAL等）
        'damage': None,         # 伤害值
        'block': None,          # 格挡值
        'magicNumber': None,    # 特殊值
        'damageUpgrade': None,  # 升级后伤害值
        'blockUpgrade': None,   # 升级后格挡值
        'magicUpgrade': None,   # 升级后特殊值
        'costUpgrade': None,    # 升级后费用
        'isNoble': False,       # 是否为宝具卡（继承自 AbsNoblePhantasmCard）
    }

    # === 匹配普通卡的 super(...) 构造器（5-7个参数）===
    # 示例：super(ID, 1, CardType.ATTACK, CardTarget.ENEMY, CardRarity.COMMON);
    super_pattern = (
        r'super\s*\(\s*'
        r'[^,]+'                      # ID 参数（忽略）
        r'\s*,\s*'
        r'([+-]?\d+|\w+)'             # 费用：可以是数字或常量（如 COST）
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardType\.(\w+)'      # 类型
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardTarget\.(\w+)'    # 目标
        r'\s*,\s*'
        r'(?:AbstractCard\.)?CardRarity\.(\w+)'    # 稀有度
        r'(?:\s*,[^)]*)?'             # 可选的后续参数（如 CardColor），全部忽略
        r'\s*\)'
    )

    # 尝试匹配普通卡构造器
    match = re.search(super_pattern, code, re.DOTALL)
    if match:
        # 提取匹配到的各个字段
        cost_str = match.group(1)     # 费用字符串
        type_str = match.group(2)     # 类型
        target_str = match.group(3)   # 目标
        rarity_str = match.group(4)   # 稀有度

        # 将解析结果存入数据字典
        data['type'] = type_str
        data['target'] = target_str
        data['rarity'] = rarity_str

        # 只有当费用是数字时才转换为整数，否则保持 None
        if re.fullmatch(r'[+-]?\d+', cost_str):
            data['cost'] = int(cost_str)
    else:
        # === 匹配宝具卡的 super(...) 构造器（type, target, cost）===
        # 示例：super(ID, CardType.ATTACK, CardTarget.ALL_ENEMY, 1);
        alt_pattern = (
            r'super\s*\(\s*'
            r'[^,]+'                       # ID 参数（忽略）
            r'\s*,\s*'
            r'(?:AbstractCard\.)?CardType\.(\w+)'      # 类型
            r'\s*,\s*'
            r'(?:AbstractCard\.)?CardTarget\.(\w+)'    # 目标
            r'\s*,\s*'
            r'([+-]?\d+|\w+)'              # 费用
            r'\s*\)'
        )
        alt_match = re.search(alt_pattern, code)
        if alt_match:
            # 提取宝具卡的字段
            type_str = alt_match.group(1)
            target_str = alt_match.group(2)
            cost_str = alt_match.group(3)

            # 宝具卡默认稀有度为 SPECIAL
            data['type'] = type_str
            data['target'] = target_str
            data['rarity'] = "SPECIAL"
            
            # 解析费用
            if re.fullmatch(r'[+-]?\d+', cost_str):
                data['cost'] = int(cost_str)
            
            # 标记为宝具卡
            data['isNoble'] = True
        else:
            # 如果两种构造器都匹配不到，打印错误信息
            print(f"⚠️ 仍无法识别 super 构造器 in {filename}.java")
            # 打印包含 super 的行用于调试
            for line in code.splitlines():
                if 'super(' in line and not line.strip().startswith("//"):
                    print(f"   → {line.strip()}")
                    break

    # === 解析 setDamage 方法 ===
    # 示例：setDamage(10, 5) → 伤害值=10，升级后=15
    dmg_match = re.search(r'setDamage\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if dmg_match:
        base = int(dmg_match.group(1))    # 基础伤害值
        delta = int(dmg_match.group(2))   # 升级增量
        data['damage'] = base
        data['damageUpgrade'] = base + delta  # 升级后伤害 = 基础 + 增量

    # === 解析 setBlock 方法 ===
    # 示例：setBlock(5, 3) → 格挡值=5，升级后=8
    blk_match = re.search(r'setBlock\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if blk_match:
        base = int(blk_match.group(1))    # 基础格挡值
        delta = int(blk_match.group(2))   # 升级增量
        data['block'] = base
        data['blockUpgrade'] = base + delta  # 升级后格挡 = 基础 + 增量

    # === 解析 setMagic 方法 ===
    # 示例：setMagic(3, 2) → 特殊值=3，升级后=5
    mag_match = re.search(r'setMagic\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)', code)
    if mag_match:
        base = int(mag_match.group(1))    # 基础特殊值
        delta = int(mag_match.group(2))   # 升级增量
        data['magicNumber'] = base
        data['magicUpgrade'] = base + delta  # 升级后特殊值 = 基础 + 增量

    # === 解析 setNP 方法（宝具值）===
    # 如果没有 setMagic，但有 setNP，则将 NP 值作为特殊值处理
    np_match = re.search(r'setNP\s*\(\s*(\d+)\s*\)', code)
    if np_match and data['magicNumber'] is None:
        val = int(np_match.group(1))
        data['magicNumber'] = val
        data['magicUpgrade'] = val  # 默认升级后值相同

    # === 解析 setCostUpgrade 方法 ===
    # 示例：setCostUpgrade(0) → 升级后费用为 0
    cost_up_match = re.search(r'setCostUpgrade\s*\(\s*(\d+)\s*\)', code)
    if cost_up_match:
        data['costUpgrade'] = int(cost_up_match.group(1))

    return data

def clean_desc(s: str) -> str:
    """
    清理描述文本中的换行符
    """
    if not s:
        return ""
    # 将 NL 替换为实际换行符
    return s.replace("NL", "\n")

def scan_java_files(dir_path: str):
    """
    递归扫描目录下的所有 Java 文件，排除基类文件
    """
    java_files = []
    # 遍历目录中的所有项目
    for item in os.listdir(dir_path):
        item_path = os.path.join(dir_path, item)
        # 如果是子目录，递归扫描
        if os.path.isdir(item_path):
            java_files.extend(scan_java_files(item_path))
        # 如果是 Java 文件，检查是否为基类文件
        elif item.endswith(".java"):
            # 定义要排除的基类文件名
            excluded_files = {'AbsNoblePhantasmCard.java', 'FateMagineerCard.java', 'FGOCard.java'}
            # 只添加非基类的 Java 文件
            if item not in excluded_files:
                java_files.append(item_path)
    return java_files

def main():
    """
    主函数：按 JSON 顺序读取卡片信息并分类输出到不同 Excel
    """
    # 1. 读取 JSON 文件，保留原始顺序
    with open(JSON_FILE, 'r', encoding='utf-8') as f:
        json_data = json.load(f)

    # 2. 扫描所有 Java 文件（排除基类），建立卡片 ID 到信息的映射
    java_card_info = {}
    java_files = scan_java_files(CARDS_DIR)
    
    # 遍历所有 Java 文件
    for java_path in java_files:
        # 提取文件名（去掉路径和 .java 后缀）作为卡片 ID
        file_name = os.path.basename(java_path)
        card_id = file_name[:-5]  # 去掉 ".java"
        
        try:
            # 读取 Java 文件内容
            with open(java_path, 'r', encoding='utf-8') as f:
                code = f.read()
            # 解析 Java 代码，获取卡片信息
            java_info = parse_java_card(code, card_id)
            # 将解析结果存入映射字典
            java_card_info[card_id] = java_info
        except Exception as e:
            # 如果读取失败，打印错误信息
            print(f"❌ 读取失败 {java_path}: {e}")

    # 3. 按 JSON 中的键顺序构建行数据，分别存入普通卡和宝具卡数组
    normal_rows = []  # 存储普通卡数据
    noble_rows = []   # 存储宝具卡数据

    # 遍历 JSON 中的键值对，保持顺序
    for full_key, json_info in json_data.items():
        # 提取卡片 ID（去掉前缀）
        if full_key.startswith(MOD_ID_PREFIX):
            card_id = full_key[len(MOD_ID_PREFIX):]
        else:
            card_id = full_key

        # 获取对应的 Java 信息，如果不存在则使用默认空值
        java_info = java_card_info.get(card_id, {
            'cost': None, 'type': '', 'target': '', 'rarity': '',
            'damage': None, 'block': None, 'magicNumber': None,
            'damageUpgrade': None, 'blockUpgrade': None, 'magicUpgrade': None,
            'costUpgrade': None, 'isNoble': False
        })

        # 构建 Excel 行数据
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

        # 根据是否为宝具卡，将数据存入对应数组
        if java_info['isNoble']:
            noble_rows.append(row)
        else:
            normal_rows.append(row)

    # 4. 生成普通卡 Excel 文件
    if normal_rows:  # 如果普通卡数组不为空
        # 创建 DataFrame
        df_normal = pd.DataFrame(normal_rows, columns=[
            "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
            "伤害值", "格挡值", "特殊值", "费用+", "强化版描述",
            "伤害值+", "格挡值+", "特殊值+"
        ])
        # 保存到 Excel
        df_normal.to_excel(OUTPUT_EXCEL_NORMAL, index=False)
        print(f"✅ 普通卡 Excel 已生成: {OUTPUT_EXCEL_NORMAL} （共 {len(normal_rows)} 张卡）")
    else:
        print('✅ 普通卡数量为 0，跳过生成普通卡 Excel')

    # 5. 生成宝具卡 Excel 文件
    if noble_rows:  # 如果宝具卡数组不为空
        # 创建 DataFrame
        df_noble = pd.DataFrame(noble_rows, columns=[
            "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
            "伤害值", "格挡值", "特殊值", "费用+", "强化版描述",
            "伤害值+", "格挡值+", "特殊值+"
        ])
        # 保存到 Excel
        df_noble.to_excel(OUTPUT_EXCEL_NOBLE, index=False)
        print(f"✅ 宝具卡 Excel 已生成: {OUTPUT_EXCEL_NOBLE} （共 {len(noble_rows)} 张卡）")
    else:
        print('✅ 宝具卡数量为 0，跳过生成宝具卡 Excel')

    print('✅ 所有 Excel 文件已按类型分类生成完毕')

if __name__ == "__main__":
    main()