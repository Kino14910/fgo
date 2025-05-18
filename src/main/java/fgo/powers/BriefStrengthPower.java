package fgo.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static fgo.FGOMod.makeID;

public class BriefStrengthPower extends BasePower {
    public static final String POWER_ID = makeID(BriefStrengthPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public BriefStrengthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "GutsTriggerPower");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    
}
