package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SchwarzwaldFalkePower extends BasePower {
    public static final String POWER_ID = makeID(SchwarzwaldFalkePower.class.getSimpleName());

    public SchwarzwaldFalkePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "DelayedBuffPower");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new EvasionPower(owner, 1), 1));
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void updateDescription() {
        description = formatDescriptionByQuantity(amount);
    }
}
