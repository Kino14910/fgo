package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WatersidePower extends BasePower {
    public static final String POWER_ID = makeID(WatersidePower.class.getSimpleName());
    private static final int BLOCK_AMT = 3;

    public WatersidePower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner);
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();

            addToBot(new GainBlockAction(owner, owner, BLOCK_AMT));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new GainBlockAction(mo, owner, BLOCK_AMT));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], BLOCK_AMT);
    }
}
