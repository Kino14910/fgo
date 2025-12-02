package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.patches.RevivePatch;

public class HeraclesGutsPower extends BasePower {
    public static final String POWER_ID = makeID(HeraclesGutsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
 
    public HeraclesGutsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "GutsPower"); 
    }

    /**
     * @see RevivePatch
     */
    @Override
    public void onSpecificTrigger() {
        AbstractDungeon.player.heal(Math.max(amount, 1), true);
        addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void onRemove() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, BriefStrengthPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}