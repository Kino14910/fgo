package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static fgo.FGOMod.makeID;

public class MillenniumCastlePower extends BasePower {
    public static final String POWER_ID = makeID(MillenniumCastlePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MillenniumCastlePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -1), -1));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            this.addToTop(new ApplyPowerAction(m, m, new StrengthPower(m, -2), -2));
        }
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    @Override
    public void onRemove() {
        if (this.owner.hasPower("Strength") && (this.owner.getPower("Strength")).amount < 0) {
            int StrAmt = 0;
            StrAmt -= (this.owner.getPower("Strength")).amount;
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, StrAmt * 2), StrAmt * 2));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, StrAmt), StrAmt));
        }
    }

    public AbstractPower makeCopy() {
        return new MillenniumCastlePower(this.owner, this.amount);
    }
}
