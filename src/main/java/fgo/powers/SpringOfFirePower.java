package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.patches.RevivePatch;

public class SpringOfFirePower extends BasePower {
    public static final String POWER_ID = makeID(SpringOfFirePower.class.getSimpleName());

    public SpringOfFirePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "GutsTriggerPower");
    }
    
    /**
     * @see RevivePatch
     */
    @Override
    public void onSpecificTrigger() {
        addToBot(new RemoveDebuffsAction(owner));
        addToBot(new ApplyPowerAction(owner, owner, new NPDamagePower(amount)));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
