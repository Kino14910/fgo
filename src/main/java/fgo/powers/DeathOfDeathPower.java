package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.PhantasmalPower;

import fgo.patches.RevivePatch;

public class DeathOfDeathPower extends BasePower {
    public static final String POWER_ID = makeID(DeathOfDeathPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;

    public DeathOfDeathPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner, owner, -1, "GutsTriggerPower");
    }

    /**
     * {@link RevivePatch}
     */
    @Override
    public void onSpecificTrigger() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new PhantasmalPower(owner, 1)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
