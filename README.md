# The Master Mod - Fate/Grand Order x 杀戮尖塔

![Mod封面](https://s2.loli.net/2025/01/19/rvBtxkDLKnhzsS8.png)

![支持语言](https://img.shields.io/badge/语言-中英文-9cf)

基于《杀戮尖塔》的Fate/Grand Order主题Mod，新增御主（The Master）作为可玩角色，融合圣晶石召唤、宝具解放等FGO经典机制。

**当前版本**: v0.0.2

## 特色内容

### 角色系统
- **全新角色**: 御主（The Master）拥有独特机制
- **职阶系统**: 通过遗物切换Saber/Archer/Lancer等职阶（开发中）

### 卡牌系统
- **75+专属卡牌**: 包含令咒、魔术礼装等特色卡牌
- **30+宝具卡牌**: 释放EX级宝具逆转战局
- **10+无色卡牌**: 通用型强力支援卡
- **20+遗物**
- **动态数值系统**: 使用`!NP!`和`!S!`实现宝具值与暴击星联动

### 特殊机制
- **宝具充能系统**: 
  - 攻击/受击积累宝具值
  - 100%时获得宝具卡
  - 可突破100%获取强化效果
- **暴击系统**:
  - 通过卡牌/遗物收集暴击星
  - 消耗10颗暴击星使下次攻击暴击(伤害翻倍)
- **诅呪机制**:
  - 高风险高收益的双向诅呪，效果类似于永续性的中毒
  - 可主动施加自身或转移给敌人
- **令咒系统**：
  - 宝具解放 : 获得 100% 宝具值 。 使用 1 划 令咒 发动。
  - 灵基修复 : 回复 30 点生命。  使用 1 划 令咒 发动。
  - 灵基复原 : 免死并回复所有生命。  获得 300% 宝具值 。在你要被杀死时显现，消耗 3 划令咒 发动。

## Mod开发指南

### 基础依赖
本Mod基于 [Basicmod](https://github.com/Alchyr/BasicMod) 开发

### 特殊变量系统
在`CardStrings.json`中使用动态占位符：
```json
  "${modID}:MorningLark": {
    "NAME": "晨之云雀",
    "DESCRIPTION": "获得 !NP! % fgo:宝具值 。 NL 获得 !S! 颗 fgo:暴击星 。 NL 在你的回合结束时，失去 20% fgo:宝具值 NL 消耗 。"
  },
```



对应`MorningLark.java`中的属性访问：

```java
    public MorningLark() {
        super(ID, INFO);
        setNP(30, 20);
        setStar(10, 10);
    }
```





## 引用声明

### 美术资源

- **宝具卡背**: 整合自[HexUILib](https://steamcommunity.com/sharedfiles/filedetails/?id=1667206983)与[Magineer Mod](https://steamcommunity.com/sharedfiles/filedetails/?id=1667220091)
- **动态背景**: 改编自废墟图书馆Mod
- **按钮样式**: 参考卡包大师Mod

### 机制参考

- **宝具条设计**: 灵感源于明日方舟斯卡蒂Mod



## 翻译协作

**急需以下方面的英语支持：**

1. 卡牌描述的本地化润色
2. FGO术语的准确翻译
3. 技能说明的符合英语习惯表达

**贡献指引**：
- 在`/localization/en/`提交翻译文件
- 使用术语对照表保持一致性
- 通过Pull Request或Steam工坊留言提交



**Urgent Call for English Language Support**

We require expert assistance in the following critical areas to enhance our FGO project's accessibility and appeal to English-speaking users:

1. **Localization and Polishing of Card Descriptions**
2. Accurate Translation of FGO Terminology
3. Skill Descriptions in Idiomatic English

**Contribution Guidelines:**

- Submit your translation files to the `/localization/en/` directory.
- Maintain consistency by referring to our terminology lookup table.
- Submit your contributions via Pull Request or through Steam Workshop comments.



---

## 开发路线

✅宝具
	|- 宝具获取
	|- 宝具界面
	|- 宝具值UI

✅暴击星
	|- 打出卡牌获得暴击星
	|- 怪物受击获得暴击星
	|- 暴击机制

✅令咒
	|- 令咒UI
	|- 令咒机制实现

🔧 水边地形 
	|- 场地状态影响卡牌效果
	|- 格挡相关卡牌
	|- 专属遗物「水天日光」

🔧 灼伤体系
	|- 火焰伤害持续叠加
	|- 「炎门」状态免疫灼伤
	|- 宝具「燃烧殆尽的炎笼」

❌ 魔术礼装
	|- 不同的魔术礼装实现对应效果
	|- 角色选择界面选择对应礼装

❌ 红卡体系
	|- 通过受伤叠加狂化层数
	|- 血越少攻击力越高
	|- 特殊遗物「十二试炼」
