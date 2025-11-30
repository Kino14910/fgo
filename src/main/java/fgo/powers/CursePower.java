package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class CursePower extends BasePower {
    public static final String POWER_ID = makeID(CursePower.class.getSimpleName());

    public CursePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount); 
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
