package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FacelessMoonPower extends BasePower {
    public static final String POWER_ID = makeID(FacelessMoonPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public FacelessMoonPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount); 
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int handAmt = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.isEthereal) {
                    c.retain = true;
                    ++handAmt;
                }
            }
            if (handAmt > 0) {
                flash();
                addToBot(new ApplyPowerAction(owner, owner, new StarPower(owner, handAmt * amount)));
            }
        }
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
