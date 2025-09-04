package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MaxHPPower extends BasePower {
    public static final String POWER_ID = makeID(MaxHPPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MaxHPPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > amount) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
            return damageAmount - amount;
        }
        if (damageAmount > 0) {
            addToTop(new ReducePowerAction(owner, owner, ID, damageAmount));
            return 0;
        }
        return damageAmount;
    }
}
