package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static fgo.FGOMod.makeID;

public class ManaBurstGemsPower extends BasePower {
    public static final String POWER_ID = makeID(ManaBurstGemsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ManaBurstGemsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "DelayedBuffPower");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    
}
