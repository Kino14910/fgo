package fgo.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import fgo.util.CardStats;

public abstract class FGOCard extends BaseCard {
    public boolean upgradedNP;
    public int npUpgrade;
    public int baseNP;
    public int np;
    public boolean upgradedStar;
    public int starUpgrade;
    public int baseStar;
    public int star;
    public boolean isModified;

    private void initValues() {
        np = baseNP = npUpgrade = 0;
        upgradedNP = false;
        star = baseStar = starUpgrade = 0; 
        upgradedStar = false;

        setCustomVar("NP", baseNP, npUpgrade);
        setCustomVar("S", baseStar, starUpgrade);
    }

    public FGOCard(String ID, CardStats INFO) {
        super(ID, INFO);
        initValues();
    }

    public FGOCard(String ID, CardStats INFO, String img) {
        super(ID, INFO, img);
        initValues();
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
        super(ID, cost, cardType, target, rarity, color);
        initValues();
    }

    public FGOCard(String ID, int cost, AbstractCard.CardType cardType, AbstractCard.CardTarget target, AbstractCard.CardRarity rarity, AbstractCard.CardColor color, String img) {
        super(ID, cost, cardType, target, rarity, color, img);
        initValues();
    }

    protected final void setNP(int np) { setNP(np, npUpgrade); }

    protected final void setNP(int np, int npUpgrade) {
        this.baseNP = this.np = np;
        if (npUpgrade != 0) {
            this.upgradedNP = true;
            this.npUpgrade = npUpgrade;
        }
    }

    protected final void setNPUpgrade(int npUpgrade) {
        this.npUpgrade = npUpgrade;
        this.upgradedNP = true;
    }

    protected final void setStar(int star) { setStar(star, starUpgrade); }

    protected final void setStar(int star, int starUpgrade) {
        this.baseStar = this.star = star;
        if (starUpgrade != 0) {
            this.upgradedStar = true;
            this.starUpgrade = starUpgrade;
        }
    }

    protected final void setStarUpgrade(int starUpgrade) {
        this.starUpgrade = starUpgrade;
        this.upgradedStar = true;
    }

    // 重写 upgrade 方法以处理 NP 和 star 升级
    @Override
    public void upgrade() {
        super.upgrade();

        if (upgradedNP) {
            this.baseNP += npUpgrade;
            this.np = this.baseNP;
        }

        if (upgradedStar) {
            this.baseStar += starUpgrade;
            this.star = this.baseStar;
        }
    }

    // 自定义方法以获取 NP 和 star 值
    public int getNP() {
        return this.np;
    }

    public int getStar() {
        return this.star;
    }

    public void setCasterBackground() {
       this.setBackgroundTexture(
               "fgo/images/512/bg_skill_MASTER_caster_s.png",
               "fgo/images/1024/bg_skill_MASTER_caster.png");
    }
}
