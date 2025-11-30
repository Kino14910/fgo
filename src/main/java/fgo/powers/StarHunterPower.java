package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class StarHunterPower extends BasePower {
    public static final String POWER_ID = makeID(StarHunterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public StarHunterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "CriticalDamageUpPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
