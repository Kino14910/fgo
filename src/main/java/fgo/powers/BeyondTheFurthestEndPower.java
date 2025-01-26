package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BeyondTheFurthestEndPower extends BasePower {
    public static final String POWER_ID = "BeyondTheFurthestEndPower";
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public BeyondTheFurthestEndPower(AbstractCreature owner, int amount) {
         super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BuffRegenPower");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, this.owner, new PoisonPower(monster, this.owner, this.amount), this.amount));
                    this.addToBot(new ApplyPowerAction(monster, this.owner, new WeakPower(monster, 1, false), 1));
                }
            }
        }

        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    
}
