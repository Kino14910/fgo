const fs = require('fs-extra');
const path = require('path');
const XLSX = require('xlsx');
const readline = require('readline');

const JSON_FILE = './zhs/CardStrings.json';
const CARDS_DIR = '../../../java/fgo/cards';
const OUTPUT_EXCEL_NORMAL = 'cards_normal.xlsx';
const OUTPUT_EXCEL_NOBLE = 'cards_noble.xlsx';

const MOD_ID_PREFIX = '${modID}:';

function parseJavaCard(code, filename = 'unknown') {
    const data = {
        cost: null, type: '', target: '', rarity: '',
        damage: null, block: null, magicNumber: null,
        np: null, star: null,
        damageUpgrade: null, blockUpgrade: null, magicUpgrade: null,
        npUpgrade: null, starUpgrade: null,
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

    const npMatch = code.match(/setNP\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)/);
    if (npMatch) {
        const base = parseInt(npMatch[1], 10);
        const delta = parseInt(npMatch[2], 10);
        data.np = base;
        data.npUpgrade = base + delta;
    }

    const starMatch = code.match(/setStar\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)/);
    if (starMatch) {
        const base = parseInt(starMatch[1], 10);
        const delta = parseInt(starMatch[2], 10);
        data.star = base;
        data.starUpgrade = base + delta;
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
            const fileName = path.basename(fullPath);
            if (!['AbsNoblePhantasmCard.java', 'FateMagineerCard.java', 'FGOCard.java'].includes(fileName)) {
                javaFiles.push(fullPath);
            }
        }
    }
    return javaFiles;
}

function importExcelToJSON() {
    const allData = {};
    
    if (fs.existsSync(OUTPUT_EXCEL_NORMAL)) {
        const wb = XLSX.readFile(OUTPUT_EXCEL_NORMAL);
        const sheet = wb.Sheets[wb.SheetNames[0]];
        const rows = XLSX.utils.sheet_to_json(sheet);
        
        rows.forEach(row => {
            const id = MOD_ID_PREFIX + row.ID;
            if (id) {
                allData[id] = {
                    NAME: row["牌名"],
                    DESCRIPTION: cleanDesc(row["描述"]),
                    UPGRADE_DESCRIPTION: cleanDesc(row["强化版描述"])
                };
            }
        });
        console.log(`✅ 导入普通卡: ${rows.length} 条`);
    }

    if (fs.existsSync(OUTPUT_EXCEL_NOBLE)) {
        const wb = XLSX.readFile(OUTPUT_EXCEL_NOBLE);
        const sheet = wb.Sheets[wb.SheetNames[0]];
        const rows = XLSX.utils.sheet_to_json(sheet);
        
        rows.forEach(row => {
            const id = row.ID;
            if (id) {
                allData[id] = {
                    NAME: row["牌名"],
                    DESCRIPTION: cleanDesc(row["描述"]),
                    UPGRADE_DESCRIPTION: cleanDesc(row["强化版描述"])
                };
            }
        });
        console.log(`✅ 导入宝具卡: ${rows.length} 条`);
    }

    fs.writeFileSync(JSON_FILE, JSON.stringify(allData, null, 2));
    console.log(`✅ JSON文件已生成: ${JSON_FILE} (${Object.keys(allData).length} 个条目)`);
}

function exportJSONToExcel() {
    const jsonData = JSON.parse(fs.readFileSync(JSON_FILE, 'utf-8'));
    const normalRows = [];
    const nobleRows = [];

    const normalHeader = [
        "ID", "牌名", "稀有度", "类型", "目标", "费用", "描述",
        "伤害值", "格挡值", "特殊值", "宝具值", "暴击星", "费用+", "强化版描述",
        "伤害值+", "格挡值+", "特殊值+", "宝具值+", "暴击星+"
    ];
    
    const nobleHeader = [
        "ID", "牌名", "类型", "目标", "费用", "描述",
        "伤害值", "格挡值", "特殊值", "宝具值", "暴击星", "费用+", "强化版描述",
        "伤害值+", "格挡值+", "特殊值+", "宝具值+", "暴击星+"
    ];

    const javaCardInfo = {};
    const javaFiles = scanJavaFiles(CARDS_DIR);
    for (const javaPath of javaFiles) {
        const cardId = path.basename(javaPath, '.java');
        try {
            const code = fs.readFileSync(javaPath, 'utf-8');
            javaCardInfo[cardId] = parseJavaCard(code, cardId);
        } catch (e) {
            console.error(`❌ 读取失败 ${javaPath}:`, e.message);
        }
    }

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
            np: null, star: null,
            damageUpgrade: null, blockUpgrade: null, magicUpgrade: null,
            npUpgrade: null, starUpgrade: null,
            costUpgrade: null, isNoble: false
        };

        const commonFields = {
            "ID": cardId,
            "牌名": jsonInfo.NAME || '',
            "类型": javaInfo.type,
            "目标": javaInfo.target,
            "费用": javaInfo.cost,
            "描述": cleanDesc(jsonInfo.DESCRIPTION || ''),
            "伤害值": javaInfo.damage,
            "格挡值": javaInfo.block,
            "特殊值": javaInfo.magicNumber,
            "宝具值": javaInfo.np,
            "暴击星": javaInfo.star,
            "费用+": javaInfo.costUpgrade,
            "强化版描述": cleanDesc(jsonInfo.UPGRADE_DESCRIPTION || ''),
            "伤害值+": javaInfo.damageUpgrade,
            "格挡值+": javaInfo.blockUpgrade,
            "特殊值+": javaInfo.magicUpgrade,
            "宝具值+": javaInfo.npUpgrade,
            "暴击星+": javaInfo.starUpgrade,
        };

        if (javaInfo.isNoble) {
            nobleRows.push(commonFields);
        } else {
            normalRows.push({
                ...commonFields,
                "稀有度": javaInfo.rarity
            });
        }
    }

    if (normalRows.length > 0) {
        const wsNormal = XLSX.utils.json_to_sheet(normalRows, { header: normalHeader });
        const wbNormal = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wbNormal, wsNormal, 'Cards');
        XLSX.writeFile(wbNormal, OUTPUT_EXCEL_NORMAL);
        console.log(`✅ 普通卡 Excel 已生成: ${OUTPUT_EXCEL_NORMAL} (${normalRows.length} 张卡)`);
    }

    if (nobleRows.length > 0) {
        const wsNoble = XLSX.utils.json_to_sheet(nobleRows, { header: nobleHeader });
        const wbNoble = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wbNoble, wsNoble, 'Noble Cards');
        XLSX.writeFile(wbNoble, OUTPUT_EXCEL_NOBLE);
        console.log(`✅ 宝具卡 Excel 已生成: ${OUTPUT_EXCEL_NOBLE} (${nobleRows.length} 张卡)`);
    }
}

async function prompt(question) {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    return new Promise(resolve => {
        rl.question(question, (answer) => {
            rl.close();
            resolve(answer);
        });
    });
}

async function main() {
    console.log(`\n===== 卡片数据管理工具 (v1.0) =====`);
    console.log(`1. 导出Excel (从Java代码生成)`);
    console.log(`2. 导入JSON (从Excel生成CardStrings.json)`);
    console.log(`================================\n`);

    if (process.argv.includes('--export')) {
        console.log("\n▶ 正在导出Excel...");
        exportJSONToExcel();
        return;
    } else if (process.argv.includes('--import')) {
        console.log("\n▶ 正在导入JSON...");
        importExcelToJSON();
        return;
    }

    const choice = await prompt("请选择操作 (输入1或2): ");
    
    if (choice === '1') {
        console.log("\n▶ 正在导出Excel...");
        exportJSONToExcel();
    } else if (choice === '2') {
        console.log("\n▶ 正在导入JSON...");
        importExcelToJSON();
    } else {
        console.log("❌ 无效选择！请使用 1 或 2");
    }
}

if (require.main === module) {
    main().catch(console.error);
}