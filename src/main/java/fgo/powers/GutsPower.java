package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import fgo.patches.RevivePatch;

public class GutsPower extends BasePower{
    public static final String POWER_ID = makeID(GutsPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
 
    public GutsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, owner, amount); 
    }

    /**
     * {@link RevivePatch}
     */
    @Override
    public void onSpecificTrigger() {
        flash();
        AbstractDungeon.player.heal(Math.max(amount, 1), true);
        addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    
}