package fgo.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static fgo.FGOMod.makeID;

public class CriticalDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(CriticalDamageUpPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CriticalDamageUpPower(AbstractCreature owner, int amount) {
         super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    
}
