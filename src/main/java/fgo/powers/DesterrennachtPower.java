package fgo.powers;

import static fgo.FGOMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DesterrennachtPower extends BasePower implements NonStackablePower{
    public static final String POWER_ID = makeID(DesterrennachtPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public DesterrennachtPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "EndOfADreamPower");
    }

    @Override
    public void updateDescription() {
        description = amount > 1 ? 
            String.format(DESCRIPTIONS[0], amount) : 
            DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
        if (amount == 0) {
            addToBot(new ApplyPowerAction(owner, owner, new StunMonsterPower((AbstractMonster) owner)));
        }
    }
}
