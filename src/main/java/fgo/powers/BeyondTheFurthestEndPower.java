package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BeyondTheFurthestEndPower extends BasePower {
    public static final String POWER_ID = makeID(BeyondTheFurthestEndPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public BeyondTheFurthestEndPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BuffRegenPower");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.isDead || m.isDying) continue;
                addToBot(new ApplyPowerAction(m, owner, new StrengthPower(m, -1)));
            }
        }
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    
   @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
