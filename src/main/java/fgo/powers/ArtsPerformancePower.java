package fgo.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static fgo.FGOMod.makeID;

public class ArtsPerformancePower extends BasePower {
    public static final String POWER_ID = makeID(ArtsPerformancePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ArtsPerformancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
}
