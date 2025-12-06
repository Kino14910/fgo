package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class CriticalDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(CriticalDamageUpPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public CriticalDamageUpPower(AbstractCreature owner, int amount) {
         super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
