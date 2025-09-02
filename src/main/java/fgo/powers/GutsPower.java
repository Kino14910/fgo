package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GutsPower extends BasePower {
    public static final String POWER_ID = makeID(GutsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int time;
 
    public GutsPower(AbstractCreature owner, int amount, int time) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
        this.time = time;
        this.amount2 = time == 1 ? 0 : time;
        this.updateDescription();
    }

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
        System.out.println(time);
        description = String.format(time == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1], amount, time);
    }

    
}