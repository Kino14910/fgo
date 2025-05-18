package fgo.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import fgo.action.FgoNpAction;

import static fgo.FGOMod.makeID;

public class GutsAtTheWellPower extends BasePower {
    public static final String POWER_ID = makeID(GutsAtTheWellPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GutsAtTheWellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "GutsTriggerPower");
    }

    @Override
    public void onSpecificTrigger() {
        this.flash();
        this.addToBot(new FgoNpAction(80));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    
}
