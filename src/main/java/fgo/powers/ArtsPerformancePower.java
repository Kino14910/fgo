package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class ArtsPerformancePower extends BasePower {
    public static final String POWER_ID = makeID(ArtsPerformancePower.class.getSimpleName());

    public ArtsPerformancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }
    
    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
