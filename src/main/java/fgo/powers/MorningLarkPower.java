package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.FgoNpAction;

public class MorningLarkPower extends BasePower {
    public static final String POWER_ID = makeID(MorningLarkPower.class.getSimpleName());

    public MorningLarkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount, "EndOfADreamPower");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new FgoNpAction(-20));
    }
}
