package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DazzlingMoonPower extends BasePower {
    public static final String POWER_ID = makeID(DazzlingMoonPower.class.getSimpleName());

    public DazzlingMoonPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToTop(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -1), -1));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToTop(new ApplyPowerAction(m, m, new StrengthPower(m, -2), -2));
        }
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void onRemove() {
        if (owner.hasPower("Strength") && (owner.getPower("Strength")).amount < 0) {
            int StrAmt = 0;
            StrAmt -= (owner.getPower("Strength")).amount;
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, StrAmt * 2), StrAmt * 2));
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, StrAmt), StrAmt));
        }
    }

    
}
