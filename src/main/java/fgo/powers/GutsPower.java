package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.patches.RevivePatch;

public class GutsPower extends BasePower {
    public static final String POWER_ID = makeID(GutsPower.class.getSimpleName());
    private int time;
 
    public GutsPower(AbstractCreature owner, int amount, int time) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
        this.time = time;
        this.amount2 = time == 1 ? 0 : time;
        this.updateDescription();
    }

    /**
     * @see RevivePatch
     */
    @Override
    public void onSpecificTrigger() {
        AbstractDungeon.player.heal(Math.max(amount, 1), true);
        time--;
        amount2 = time == 1 ? 0 : time;
        if (time == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
        } 
        
        updateDescription();
    }

    @Override
    public void onRemove() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, BriefStrengthPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = String.format(time == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1], amount, time);
    }

    
}