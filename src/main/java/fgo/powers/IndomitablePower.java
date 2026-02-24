package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.patches.RevivePatch;

public class IndomitablePower extends BasePower {
    public static final String POWER_ID = makeID(IndomitablePower.class.getSimpleName());

    public IndomitablePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, owner, amount, "GutsTriggerPower");
    }

    /**
     * {@link RevivePatch}
     */
    @Override
    public void onSpecificTrigger() {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
    }
}
