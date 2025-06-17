package fgo.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import fgo.FGOMod;
import fgo.util.CardStats;

public abstract class FGOCard extends BaseCard {
    public boolean upgradeNP;
    public boolean upgradeStar;

    public int npUpgrade;
    public int starUpgrade;

    public int baseNP;
    public int baseStar;

    public int np;
    public int star;

    public boolean isModified;

    protected boolean upgradedNP;
    protected boolean upgradedStar;

    private void initValues() {
        np = baseNP = npUpgrade = 0;
        upgradeNP = false;
        star = baseStar = starUpgrade = 0; 
        upgradeStar = false;

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

    protected final void setNP(int np) { setNP(np, 0); }

    protected final void setNP(int np, int npUpgrade) {
        this.baseNP = this.np = np;
        if (npUpgrade != 0) {
            this.upgradeNP = true;
            this.npUpgrade = npUpgrade;
        }
    }

    protected final void upgradeNP(int amount) {
        this.np = this.baseNP += amount;
        this.upgradedNP = true;
    }

    protected final void setStar(int star) { setStar(star, 0); }

    protected final void setStar(int star, int starUpgrade) {
        this.baseStar = this.star = star;
        if (starUpgrade != 0) {
            this.upgradeStar = true;
            this.starUpgrade = starUpgrade;
        }
    }

    protected final void upgradeStar(int amount) {
        this.star = this.baseStar += amount;
        this.upgradedStar = true;
    }

    @Override
    public void upgrade() {
        if (upgraded) {
            return;
        }

        this.upgradeName();

        if (this.upgradesDescription) {
            if (cardStrings.UPGRADE_DESCRIPTION == null) {
                FGOMod.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
            } else {
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            }
        }

        if (upgradeCost) {
            if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                int diff = this.costUpgrade - this.baseCost; // how the upgrade alters cost
                this.upgradeBaseCost(this.cost + diff);
                if (this.cost < 0)
                    this.cost = 0;
            } else {
                upgradeBaseCost(costUpgrade);
            }
        }

        if (upgradeDamage)
            this.upgradeDamage(damageUpgrade);

        if (upgradeBlock)
            this.upgradeBlock(blockUpgrade);

        if (upgradeMagic)
            this.upgradeMagicNumber(magicUpgrade);

        for (LocalVarInfo var : cardVariables.values()) {
            upgradeCustomVar(var);
        }

        if (baseExhaust ^ upgExhaust)
            this.exhaust = upgExhaust;

        if (baseInnate ^ upgInnate)
            this.isInnate = upgInnate;

        if (baseEthereal ^ upgEthereal)
            this.isEthereal = upgEthereal;

        if (baseRetain ^ upgRetain)
            this.selfRetain = upgRetain;

        this.initializeDescription();
        
        if (upgradeNP) {
            upgradeNP(npUpgrade);
        }

        if (upgradeStar) {
            upgradeStar(starUpgrade);
        }
    }

    public int getNP() {
        return this.np;
    }

    public int getStar() {
        return this.star;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard candidate = super.makeStatEquivalentCopy();

        if (candidate instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) candidate;
            fgoCard.np = this.np;
            fgoCard.star = this.star;
            fgoCard.baseNP = this.baseNP;
            fgoCard.baseStar = this.baseStar;
            fgoCard.upgradeNP = this.upgradeNP;
            fgoCard.upgradeStar = this.upgradeStar;
        }

        return candidate;
    }
}
