package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.PhantasmalPower;

public class DeathOfDeathPower extends BasePower {
    public static final String POWER_ID = makeID(DeathOfDeathPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DeathOfDeathPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "GutsTriggerPower");
    }

    @Override
    public void onSpecificTrigger() {
        addToBot(new ApplyPowerAction(owner, owner, new PhantasmalPower(owner, 1)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
