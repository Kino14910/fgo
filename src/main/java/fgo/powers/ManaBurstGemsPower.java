package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ManaBurstGemsPower extends BasePower {
    public static final String POWER_ID = makeID(ManaBurstGemsPower.class.getSimpleName());

    public ManaBurstGemsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "DelayedBuffPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

}
