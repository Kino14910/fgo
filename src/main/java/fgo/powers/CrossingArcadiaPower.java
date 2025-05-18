package fgo.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static fgo.FGOMod.makeID;

public class CrossingArcadiaPower extends BasePower {
    public static final String POWER_ID = makeID(CrossingArcadiaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CrossingArcadiaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            /*if (this.owner.hasPower("Vigor")) {
                int VigorAmt = this.owner.getPower("Vigor").amount;
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, VigorAmt), VigorAmt));
            }*/
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    
}
