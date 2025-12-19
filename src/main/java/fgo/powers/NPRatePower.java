package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class NPRatePower extends BasePower {
    public static final String POWER_ID = makeID(NPRatePower.class.getSimpleName());

    public NPRatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
    }

    @Override
    public void updateDescription() {
        description = amount > 1 ? 
            String.format(DESCRIPTIONS[1], amount) : 
            DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }
}
