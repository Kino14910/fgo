package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SealNPPower extends BasePower {
    public static final String POWER_ID = makeID(SealNPPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;

    public SealNPPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount); 
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(owner, owner, ID, 1));
        }
    }
    
}
