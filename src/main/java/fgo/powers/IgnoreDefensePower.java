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
    private static final PowerType TYPE = PowerType.BUFF;

    public IgnoreDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.currentBlock > 0) {
                this.addToBot(new LoseHPAction(mo, this.owner, mo.currentBlock, AbstractGameAction.AttackEffect.SHIELD));
                this.addToBot(new ApplyPowerAction(mo, this.owner, new VulnerablePower(mo, this.amount, false),
                        this.amount, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

}
