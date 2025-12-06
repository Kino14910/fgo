package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.NightlessCharismaAction;

public class NightlessCharismaPower extends BasePower {
    public static final String POWER_ID = makeID(NightlessCharismaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public NightlessCharismaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "SunlightPower");
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new NightlessCharismaAction(amount, AbstractCard.CardType.ATTACK));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
