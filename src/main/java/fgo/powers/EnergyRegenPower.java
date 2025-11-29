package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.action.FgoNpAction;

public class EnergyRegenPower extends BasePower {
    public static final String POWER_ID = makeID(EnergyRegenPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public EnergyRegenPower(AbstractCreature owner, int npAmount) {
        super(POWER_ID, TYPE, false, owner, npAmount);
        this.amount = npAmount;
    }

    public EnergyRegenPower(AbstractCreature owner, int npAmount, int times) {
        super(POWER_ID, TYPE, true, owner, times);
        this.amount = npAmount;
        this.amount2 = times;
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
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

    public AbstractPower makeCopy() {
        return isTurnBased
                ? new EnergyRegenPower(owner, amount, amount2)
                : new EnergyRegenPower(owner, amount);
    }
}
