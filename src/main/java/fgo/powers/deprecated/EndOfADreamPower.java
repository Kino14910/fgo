package fgo.powers.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.powers.BasePower;

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
            this.flash();
            this.addToBot((AbstractGameAction)new ExhaustAction(this.amount, true, false, false));
        }
    }
//    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    
}
