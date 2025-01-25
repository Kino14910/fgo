package fgo.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class GutsPower extends BasePower {
    public static final String POWER_ID = makeID(GutsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int time;
 
    public GutsPower(AbstractCreature owner, int amount, int time) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
        this.time = time;
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new HealAction(this.owner, this.owner, this.amount));
        --this.time;
        if (this.time == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        this.updateDescription();
    }

    @Override
    public void onRemove() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, BriefStrengthPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        if (this.time == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.amount);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.time);
        }
    }

    public AbstractPower makeCopy() {
        return new GutsPower(this.owner, this.amount, time);
    }
}