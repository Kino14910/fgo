package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.FgoNpAction;

public class ArchetypeORTPower extends BasePower {
    public static final String POWER_ID = makeID(ArchetypeORTPower.class.getSimpleName());

    public ArchetypeORTPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner);
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
