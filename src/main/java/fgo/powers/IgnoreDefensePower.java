package fgo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static fgo.FGOMod.makeID;

public class IgnoreDefensePower extends BasePower {
    public static final String POWER_ID = makeID(IgnoreDefensePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public IgnoreDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
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
                this.addToBot(new ApplyPowerAction(mo, this.owner, new VulnerablePower(mo, this.amount, false), this.amount, AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new RemoveAllBlockAction(mo, this.owner));
            }
        }
    }

    public AbstractPower makeCopy() {
        return new IgnoreDefensePower(this.owner, this.amount);
    }
}
