package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.action.FgoNpAction;

public class EnergyRegenPower extends BasePower {
    public static final String POWER_ID = makeID(EnergyRegenPower.class.getSimpleName());

    public EnergyRegenPower(AbstractCreature owner, int npAmount) {
        super(POWER_ID, PowerType.BUFF, false, owner, npAmount);
        amount = npAmount;
    }

    public EnergyRegenPower(AbstractCreature owner, int npAmount, int times) {
        super(POWER_ID, PowerType.BUFF, true, owner, times);
        amount = npAmount;
        amount2 = times;
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new FgoNpAction(amount));
            if(isTurnBased)
                addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    public AbstractPower makeCopy() {
        return isTurnBased
                ? new EnergyRegenPower(owner, amount, amount2)
                : new EnergyRegenPower(owner, amount);
    }
}
