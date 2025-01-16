package fgo.cards;

import fgo.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class FGOCard extends BaseCard {
    protected int baseNP;
    protected int np;
    protected boolean upgradeNP;
    protected int npUpgrade;

    public FGOCard(String ID, CardStats info) {
        super(ID, info);
        // 初始化 NP 相关字段
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradeNP = false;
        this.npUpgrade = 0;

        // 设置初始的 NP 值
        this.setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, CardStats info, String cardImage) {
        super(ID, info, cardImage);
        // 初始化 NP 相关字段
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradeNP = false;
        this.npUpgrade = 0;

        // 设置初始的 NP 值
        this.setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
        super(ID, cost, cardType, target, rarity, color);
        // 初始化 NP 相关字段
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradeNP = false;
        this.npUpgrade = 0;

        // 设置初始的 NP 值
        this.setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color, String cardImage) {
        super(ID, cost, cardType, target, rarity, color, cardImage);
        // 初始化 NP 相关字段
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradeNP = false;
        this.npUpgrade = 0;

        // 设置初始的 NP 值
        this.setCustomVar("NP", baseNP, npUpgrade);
    }

    // 设置 NP 值
    protected final void setNP(int np) {
        this.setNP(np, 0);
    }

    protected final void setNP(int np, int npUpgrade) {
        this.baseNP = this.np = np;
        if (npUpgrade != 0) {
            this.upgradeNP = true;
            this.npUpgrade = npUpgrade;
        }
    }

    // 设置 NP 升级
    protected final void setNPUpgrade(int npUpgrade) {
        this.npUpgrade = npUpgrade;
        this.upgradeNP = true;
    }

    // 重写 upgrade 方法以处理 NP 升级
    @Override
    public void upgrade() {
        super.upgrade();

        if (upgradeNP) {
            this.baseNP += npUpgrade;
            this.np = this.baseNP;
            this.cardVariables.get("NP").base += npUpgrade;
            this.cardVariables.get("NP").value = this.baseNP;
            this.cardVariables.get("NP").upgraded = true;
        }
    }

    // 重写 applyPowers 方法以处理 NP 值计算
    @Override
    public void applyPowers() {
        super.applyPowers();
        // 更新 NP 值
        this.cardVariables.get("NP").value = this.np;
    }

    // 重写 calculateCardDamage 方法以处理 NP 值计算
    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        // 更新 NP 值
        this.cardVariables.get("NP").value = this.np;
    }

    // 重写 resetAttributes 方法以重置 NP 值
    @Override
    public void resetAttributes() {
        super.resetAttributes();
        this.np = this.baseNP;
    }

    // 自定义方法以获取 NP 值
    public int getNP() {
        return this.np;
    }

    public int getBaseNP() {
        return this.baseNP;
    }

    public boolean isNPModified() {
        LocalVarInfo var = this.cardVariables.get("NP");
        if (var == null) return false;
        return var.isModified();
    }

    public boolean isNPUpgraded() {
        LocalVarInfo var = this.cardVariables.get("NP");
        if (var == null) return false;
        return var.upgraded;
    }

    protected final void setCasterBg(){
        this.setBackgroundTexture(
               "fgo/images/512/bg_skill_MASTER_caster_s.png",
               "fgo/images/1024/bg_skill_MASTER_caster.png");
    }
}
