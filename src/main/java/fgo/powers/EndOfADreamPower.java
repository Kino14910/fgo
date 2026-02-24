package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EndOfADreamPower extends BasePower {
    public static final String POWER_ID = makeID(EndOfADreamPower.class.getSimpleName());
    public EndOfADreamPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, owner, 1); 
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty()) {
            flash();
            addToBot(new ExhaustAction(1, true, false, false));
        }
    }
   @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    
}
