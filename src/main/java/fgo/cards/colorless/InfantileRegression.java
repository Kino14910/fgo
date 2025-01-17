package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.powers.InfiniteGrowthPower;

public class InfantileRegression extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("InfantileRegression");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/InfantileRegression.png";
    private static final int COST = 0;
    public static final String ID = "InfantileRegression";
    public InfantileRegression() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        //this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new InfantileRegression();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new RemoveSpecificPowerAction(p, p, HugeScalePower.POWER_ID));
        if (p.hasPower(InfiniteGrowthPower.POWER_ID)) {
            int MaxHPAmt = p.getPower(InfiniteGrowthPower.POWER_ID).amount;
            if (MaxHPAmt >= this.magicNumber) {
                this.addToBot(new GainEnergyAction(MaxHPAmt / this.magicNumber));
            }
            this.addToBot(new RemoveSpecificPowerAction(p, p, InfiniteGrowthPower.POWER_ID));
        }
    }
}
