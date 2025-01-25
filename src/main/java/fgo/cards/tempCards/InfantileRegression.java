package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.InfiniteGrowthPower;
import fgo.util.CardStats;

public class InfantileRegression extends FGOCard {
    public static final String ID = makeID(InfantileRegression.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );
    public InfantileRegression() {
        super(ID, INFO);
        setMagic(4, -1);
        setSelfRetain();
//        setExhaust();
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
