package fgo.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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
        int healAmt = amount;
        if (healAmt < 1) {
            healAmt = 1;
        }
        AbstractDungeon.player.heal(healAmt, true);
        --time;
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
        if (time == 1) {
            description = String.format(DESCRIPTIONS[0], amount);
        } else {
            description = String.format(DESCRIPTIONS[1], amount, time);
        }
    }

    
}