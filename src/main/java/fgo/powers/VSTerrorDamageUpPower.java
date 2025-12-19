package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class VSTerrorDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(VSTerrorDamageUpPower.class.getSimpleName());
    
    public VSTerrorDamageUpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, true, owner, amount, "AttackUpPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}
