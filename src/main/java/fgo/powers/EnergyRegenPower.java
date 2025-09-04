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
    private final int npAmount;
    private boolean isTurnBased = false;

    public EnergyRegenPower(AbstractCreature owner, int npAmount) {
        super(POWER_ID, TYPE, false, owner, npAmount);
        this.npAmount = npAmount;
        this.isTurnBased = false;
    }

    public EnergyRegenPower(AbstractCreature owner, int npAmount, int amount) {
        super(POWER_ID, TYPE, true, owner, amount);
        this.npAmount = npAmount;
        this.isTurnBased = true;
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new FgoNpAction(npAmount));
            if(isTurnBased)
                addToBot(new ReducePowerAction(owner, owner, ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        
        description = String.format(DESCRIPTIONS[0], npAmount);
    }

    public AbstractPower makeCopy() {
        return isTurnBased
                ? new EnergyRegenPower(owner, amount, npAmount)
                : new EnergyRegenPower(owner, amount);
    }
}
