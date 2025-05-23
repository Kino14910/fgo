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

    @Override
    public void updateDescription() {this.description = DESCRIPTIONS[0];}

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToTop(new RemoveDebuffsAction(this.owner));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    
}
