package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BuffBlockPower extends BasePower {
    public static final String POWER_ID = makeID(BuffBlockPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    
    public BuffBlockPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.BUFF && source == this.owner && target == this.owner && power.ID.equals("Strength")) {
            this.addToBot(new RemoveSpecificPowerAction(target, source, power));
            power.updateDescription();

            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    
}
