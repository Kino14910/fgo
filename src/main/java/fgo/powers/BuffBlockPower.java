package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BuffBlockPower extends BasePower {
    public static final String POWER_ID = makeID(BuffBlockPower.class.getSimpleName());
    
    public BuffBlockPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.BUFF && source == owner && target == owner && power.ID.equals(StrengthPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(target, source, power));
            power.updateDescription();

            addToBot(new ReducePowerAction(owner, owner, ID, 1));
        }
    }

    
}
