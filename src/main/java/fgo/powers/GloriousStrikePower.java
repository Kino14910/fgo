package fgo.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import static fgo.FGOMod.makeID;

public class GloriousStrikePower extends BasePower {
    public static final String POWER_ID = makeID(GloriousStrikePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GloriousStrikePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }
    
    public void updateDescription() {
        this.description = this.amount <= 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToTop(new RemoveDebuffsAction(owner));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
