package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class LoseCritDamagePower extends BasePower {
    public static final String POWER_ID = makeID(LoseCritDamagePower.class.getSimpleName());

    public LoseCritDamagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new CriticalDamageUpPower(owner, -amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}
