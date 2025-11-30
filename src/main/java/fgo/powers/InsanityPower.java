package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.action.FgoNpAction;

public class InsanityPower extends BasePower {
    public static final String POWER_ID = makeID(InsanityPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public InsanityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "FightToDeathPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && this.owner.hasPower(CursePower.POWER_ID)) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
            this.addToBot(new FgoNpAction(this.amount));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, CursePower.POWER_ID, 1));
        }
    }

}
