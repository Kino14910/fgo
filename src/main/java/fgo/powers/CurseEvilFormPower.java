package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static fgo.FGOMod.makeID;

public class CurseEvilFormPower extends BasePower {
    public static final String POWER_ID = makeID(CurseEvilFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public CurseEvilFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "CursePower");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CursePower(this.owner, this.amount), this.amount));
    }

    
}
