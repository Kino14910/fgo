package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class CurseEvilFormPower extends BasePower {
    public static final String POWER_ID = makeID(CurseEvilFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;

    public CurseEvilFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "CursePower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ApplyPowerAction(owner, owner, new CursePower(owner, amount)));
    }

    
}
