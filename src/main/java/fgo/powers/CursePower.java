package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class CursePower extends BasePower {
    public static final String POWER_ID = makeID(CursePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public CursePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!this.owner.hasPower(GloriousStrikePower.POWER_ID)) {
            this.flash();
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
        }
    }

    
}
