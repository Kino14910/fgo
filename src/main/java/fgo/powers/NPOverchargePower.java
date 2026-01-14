package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NPOverChargePower extends BasePower {
    public static final String POWER_ID = makeID(NPOverChargePower.class.getSimpleName());
    private static final int MAX_AMOUNT = 4;

    public NPOverChargePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, AbstractDungeon.player, Math.min(amount, MAX_AMOUNT));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, MAX_AMOUNT);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flash();
            addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= MAX_AMOUNT) {
            this.amount = MAX_AMOUNT;
        }
    }
}
