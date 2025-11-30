package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class BriefStrengthPower extends BasePower {
    public static final String POWER_ID = makeID(BriefStrengthPower.class.getSimpleName());

    public BriefStrengthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount, "GutsTriggerPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
