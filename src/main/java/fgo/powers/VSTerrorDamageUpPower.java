package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class VSTerrorDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(VSTerrorDamageUpPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public VSTerrorDamageUpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, true, owner, amount, "AttackUpPower");
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}
