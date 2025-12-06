package fgo.powers;

import static fgo.FGOMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.patches.RevivePatch;

public class NonStackableGutsPower extends BasePower implements NonStackablePower{
    public static final String POWER_ID = makeID(NonStackableGutsPower.class.getSimpleName());
    private int time;
 
    public NonStackableGutsPower(AbstractCreature owner, int amount, int time) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "GutsPower"); 
        this.time = time;
        amount2 = time == 1 ? 0 : time;
        updateDescription();
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
        if (time == 1){
            description = String.format(DESCRIPTIONS[0], amount);
        } else {
            description = String.format(DESCRIPTIONS[1], time, amount);
        }
    }

    
}