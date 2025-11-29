const fs = require('fs-extra');
const path = require('path');
const XLSX = require('xlsx');

const JSON_FILE = './zhs/CardStrings.json';
const CARDS_DIR = '../../../java/fgo/cards';
const OUTPUT_EXCEL_NORMAL = 'cards_normal.xlsx';
const OUTPUT_EXCEL_NOBLE = 'cards_noble.xlsx';
const MOD_ID_PREFIX = '${modID}:';

function parseJavaCard(code, filename = 'unknown') {
    const data = {
        cost: null, type: '', target: '', rarity: '',
        damage: null, block: null, magicNumber: null,
        damageUpgrade: null, blockUpgrade: null, magicUpgrade: null,
        costUpgrade: null, isNoble: false,
    };

    const superPattern = /super\s*\(\s*[^,]+\s*,\s*([+-]?\d+|\w+)\s*,\s*(?:AbstractCard\.)?CardType\.(\w+)\s*,\s*(?:AbstractCard\.)?CardTarget\.(\w+)\s*,\s*(?:AbstractCard\.)?CardRarity\.(\w+)(?:\s*,[^)]*)?\s*\)/;
    const match = code.match(superPattern);

    if (match) {
        const [, costStr, typeStr, targetStr, rarityStr] = match;
        data.type = typeStr;
        data.target = targetStr;
        data.rarity = rarityStr;
        if (/^[+-]?\d+$/.test(costStr)) {
            data.cost = parseInt(costStr, 10);
        }
    } else {
        const altPattern = /super\s*\(\s*[^,]+\s*,\s*(?:AbstractCard\.)?CardType\.(\w+)\s*,\s*(?:AbstractCard\.)?CardTarget\.(\w+)\s*,\s*([+-]?\d+|\w+)\s*\)/;
        const altMatch = code.match(altPattern);
        if (altMatch) {
            const [, typeStr, targetStr, costStr] = altMatch;
            data.type = typeStr;
            data.target = targetStr;
            data.rarity = 'SPECIAL';
            if (/^[+-]?\d+$/.test(costStr)) {
                data.cost = parseInt(costStr, 10);
            }
            data.isNoble = true;
        } else {
            console.log(`⚠️ 仍无法识别 super 构造器 in ${filename}.java`);
            const superLine = code.split('\n').find(line => line.includes('super(') && !line.trim().startsWith('//'));
            if (superLine) console.log(`   → ${superLine.trim()}`);
        }
    }

    const dmgMatch = code.match(/setDamage\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)/);
    if (dmgMatch) {
        const base = parseInt(dmgMatch[1], 10);
        const delta = parseInt(dmgMatch[2], 10);
        data.damage = base;
        data.damageUpgrade = base + delta;
    }

    const blkMatch = code.match(/setBlock\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)/);
    if (blkMatch) {
        const base = parseInt(blkMatch[1], 10);
        const delta = parseInt(blkMatch[2], 10);
        data.block = base;
        data.blockUpgrade = base + delta;
    }

    const magMatch = code.match(/setMagic\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)/);
    if (magMatch) {
        const base = parseInt(magMatch[1], 10);
        const delta = parseInt(magMatch[2], 10);
        data.magicNumber = base;
        data.magicUpgrade = base + delta;
    }

    const npMatch = code.match(/setNP\s*\(\s*(\d+)\s*\)/);
    if (npMatch && data.magicNumber === null) {
        const val = parseInt(npMatch[1], 10);
        data.magicNumber = val;
        data.magicUpgrade = val;
    }

    const costUpMatch = code.match(/setCostUpgrade\s*\(\s*(\d+)\s*\)/);
    if (costUpMatch) {
        data.costUpgrade = parseInt(costUpMatch[1], 10);
    }

    return data;
}

function cleanDesc(s) {
    if (!s) return '';
    return s.replace(/NL/g, '\n');
}

function scanJavaFiles(dir) {
    const javaFiles = [];
    const items = fs.readdirSync(dir);
    for (const item of items) {
        const fullPath = path.join(dir, item);
        if (fs.statSync(fullPath).isDirectory()) {
            javaFiles.push(...scanJavaFiles(fullPath));
        } else if (item.endsWith('.java')) {
            // 排除基类文件
            const fileName = path.basename(fullPath);
            if (!['AbsNoblePhantasmCard.java', 'FateMagineerCard.java', 'FGOCard.java'].includes(fileName)) {
                javaFiles.push(fullPath);
            }
        }
    }
    return javaFiles;
}

function main() {
    const jsonData = JSON.parse(fs.readFileSync(JSON_FILE, 'utf-8'));

    const javaCardInfo = {};
    const javaFiles = scanJavaFiles(CARDS_DIR);
    for (const javaPath of javaFiles) {
        const fileName = path.basename(javaPath);
        const cardId = fileName.slice(0, -5);
        try {
            const code = fs.readFileSync(javaPath, 'utf-8');
            const javaInfo = parseJavaCard(code, cardId);
            javaCardInfo[cardId] = javaInfo;
        } catch (e) {
            console.error(`❌ 读取失败 ${javaPath}:`, e.message);
        }
    }

    const normalRows = [];
    const nobleRows = [];

    for (const [fullKey, jsonInfo] of Object.entries(jsonData)) {
        let cardId;
        if (fullKey.startsWith(MOD_ID_PREFIX)) {
            cardId = fullKey.slice(MOD_ID_PREFIX.length);
        } else {
            cardId = fullKey;
        }

        const javaInfo = javaCardInfo[cardId] || {
            cost: null, type: '', target: '', rarity: '',
            damage: null, block: null, magicNumber: null,
            damageUpgrade: null, blockUpgrade: null, magicUpgrade: null,
            costUpgrade: null, isNoble: false
        };

        const row = {
            "ID": cardId,
            "牌名": jsonInfo.NAME || '',
            "稀有度": javaInfo.rarity,
            "类型": javaInfo.type,
            "目标": javaInfo.target,
            "费用": javaInfo.cost,
            "描述": cleanDesc(jsonInfo.DESCRIPTION || ''),
            "伤害值": javaInfo.damage,
            "格挡值": javaInfo.block,
            "特殊值": javaInfo.magicNumber,
            "费用+": javaInfo.costUpgrade,
            "强化版描述": cleanDesc(jsonInfo.UPGRADE_DESCRIPTION || ''),
            "伤害值+": javaInfo.damageUpgrade,
            "格挡值+": javaInfo.blockUpgrade,
            "特殊值+": javaInfo.magicUpgrade,
        };

        if (javaInfo.isNoble) {
            nobleRows.push(row);
        } else {
            normalRows.push(row);
        }
    }

    if (normalRows.length > 0) {
        const wsNormal = XLSX.utils.json_to_sheet(normalRows, {
            header: [
                "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
                "伤害值", "格挡值", "特殊值", "费用+", "强化版描述",
                "伤害值+", "格挡值+", "特殊值+"
            ]
        });
        const wbNormal = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wbNormal, wsNormal, 'Cards');
        XLSX.writeFile(wbNormal, OUTPUT_EXCEL_NORMAL);
        console.log(`✅ 普通卡 Excel 已生成: ${OUTPUT_EXCEL_NORMAL} （共 ${normalRows.length} 张卡）`);
    } else {
        console.log('✅ 普通卡数量为 0，跳过生成普通卡 Excel');
    }

    if (nobleRows.length > 0) {
        const wsNoble = XLSX.utils.json_to_sheet(nobleRows, {
            header: [
                "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
                "伤害值", "格挡值", "特殊值", "费用+", "强化版描述",
                "伤害值+", "格挡值+", "特殊值+"
            ]
        });
        const wbNoble = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wbNoble, wsNoble, 'Noble Cards');
        XLSX.writeFile(wbNoble, OUTPUT_EXCEL_NOBLE);
        console.log(`✅ 宝具卡 Excel 已生成: ${OUTPUT_EXCEL_NOBLE} （共 ${nobleRows.length} 张卡）`);
    } else {
        console.log('✅ 宝具卡数量为 0，跳过生成宝具卡 Excel');
    }

    console.log('✅ 所有 Excel 文件已按类型分类生成完毕');
}

if (require.main === module) {
    main();
}