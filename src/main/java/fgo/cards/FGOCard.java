package fgo.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import fgo.util.CardStats;

public abstract class FGOCard extends BaseCard {
    public boolean upgradedNP;
    public int npUpgrade;
    public int baseNP;
    public int np;
    public boolean isModified;

    public FGOCard(String ID, CardStats INFO) {
        super(ID, INFO);
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradedNP = false;
        this.npUpgrade = 0;

        setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, CardStats INFO, String img) {
        super(ID, INFO, img);
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradedNP = false;
        this.npUpgrade = 0;

        setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
        super(ID, cost, cardType, target, rarity, color);
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradedNP = false;
        this.npUpgrade = 0;

        setCustomVar("NP", baseNP, npUpgrade);
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color, String img) {
        super(ID, cost, cardType, target, rarity, color, img);
        this.baseNP = 0;
        this.np = this.baseNP;
        this.upgradedNP = false;
        this.npUpgrade = 0;

        setCustomVar("NP", baseNP, npUpgrade);
    }
    
    // 设置 NP 值
    protected final void setNP(int np) { setNP(np, npUpgrade); }

    protected final void setNP(int np, int npUpgrade) {
        this.baseNP = this.np = np;
        if (npUpgrade != 0) {
            this.upgradedNP = true;
            this.npUpgrade = npUpgrade;
        }
    }

    // 设置 NP 升级
    protected final void setNPUpgrade(int npUpgrade) {
        this.npUpgrade = npUpgrade;
        this.upgradedNP = true;
    }

    // 重写 upgrade 方法以处理 NP 升级
    @Override
    public void upgrade() {
        super.upgrade();

        if (upgradedNP) {
            upgradeNP(npUpgrade);
        }
    }

    protected void upgradeNP(int amount) {
            this.baseNP += amount;
            this.np = this.baseNP;
            this.upgradedNP = true;
    }

    // 自定义方法以获取 NP 值
    public int getNP() {
        return this.np;
    }

    public void setCasterBackground() {
       this.setBackgroundTexture(
               "fgo/images/512/bg_skill_MASTER_caster_s.png",
               "fgo/images/1024/bg_skill_MASTER_caster.png");
    }
}
