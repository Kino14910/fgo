package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class HugeScalePower extends BasePower {
    public static final String POWER_ID = makeID(HugeScalePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HugeScalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BuffRegenPower");
    }

    @Override
    public void updateDescription() {this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}

    @Override
    public void atStartOfTurn() {
        this.flash();
        /*AbstractDungeon.player.increaseMaxHp(this.amount, true);
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MaxHPPower(this.owner, this.amount), this.amount));*/
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new InfiniteGrowthPower(this.owner, this.amount), this.amount));
    }

    
}
