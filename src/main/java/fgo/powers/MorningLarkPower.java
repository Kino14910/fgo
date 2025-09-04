package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.FgoNpAction;

public class MorningLarkPower extends BasePower {
    public static final String POWER_ID = makeID(MorningLarkPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public MorningLarkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "EndOfADreamPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
//
//    @Override
//    public void onInitialApplication() {
//        AbstractDungeon.player.gameHandSize -= this.amount;
//    }
//
//    @Override
//    public void onRemove() {
//        AbstractDungeon.player.gameHandSize += this.amount;
//    }
//
//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new FgoNpAction(-20));
    }

    
}
