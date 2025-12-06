package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class LoseArtsPerformancePower extends BasePower {
    public static final String POWER_ID = makeID(LoseArtsPerformancePower.class.getSimpleName());

    public LoseArtsPerformancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount, "ArtsResistancePower");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
      flash();
      addToBot(new ApplyPowerAction(owner, owner, new ArtsPerformancePower(owner, -amount)));
      addToBot(new RemoveSpecificPowerAction(owner, owner, ArtsPerformancePower.POWER_ID));
    }
    
    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
