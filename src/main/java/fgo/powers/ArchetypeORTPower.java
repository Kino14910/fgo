package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.FgoNpAction;

public class ArchetypeORTPower extends BasePower {
    public static final String POWER_ID = makeID(ArchetypeORTPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ArchetypeORTPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new StarPower(owner, 10), 10));
        addToBot(new FgoNpAction(3));
    }

    
}
