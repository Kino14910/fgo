package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import static fgo.FGOMod.makeID;

public class SchwarzwaldFalkePower extends BasePower {
    public static final String POWER_ID = makeID(SchwarzwaldFalkePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SchwarzwaldFalkePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "DelayedBuffPower");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new EvasionPower(owner, 1), 1));
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[1];
        } else {
            description = String.format(DESCRIPTIONS[0], amount);
        }
    }

    
}
