package fgo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static fgo.FGOMod.makeID;

public class EndOfADreamPower extends BasePower {
    public static final String POWER_ID = makeID(EndOfADreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public EndOfADreamPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner); 
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty()) {
            flash();
            addToBot((AbstractGameAction)new ExhaustAction(1, true, false, false));
        }
    }
//    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    
}
