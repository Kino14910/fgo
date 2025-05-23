package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.action.FgoNpAction;

import static fgo.FGOMod.makeID;

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
            this.flash();
            this.addToBot(new FgoNpAction(this.npAmount));
            if(isTurnBased)
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.npAmount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {
        return isTurnBased
                ? new EnergyRegenPower(this.owner, this.amount, this.npAmount)
                : new EnergyRegenPower(this.owner, this.amount);
    }
}
