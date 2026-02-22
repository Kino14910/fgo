package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.PhantasmalPower;

import fgo.patches.RevivePatch;

public class DeathOfDeathPower extends BasePower {
    public static final String POWER_ID = makeID(DeathOfDeathPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public DeathOfDeathPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, owner, amount, "GutsTriggerPower");
    }

    /**
     * {@link RevivePatch}
     */
    @Override
    public void onSpecificTrigger() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new PhantasmalPower(owner, 1)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
