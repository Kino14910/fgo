package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class IgnoreDefensePower extends BasePower {
    public static final String POWER_ID = makeID(IgnoreDefensePower.class.getSimpleName());

    public IgnoreDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.currentBlock > 0) {
                addToBot(new LoseHPAction(mo, owner, mo.currentBlock, AbstractGameAction.AttackEffect.SHIELD));
                addToBot(new ApplyPowerAction(mo, owner, new VulnerablePower(mo, amount, false),
                        amount, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

}
