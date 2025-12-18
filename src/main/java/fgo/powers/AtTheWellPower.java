package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import fgo.action.FgoNpAction;

public class AtTheWellPower extends BasePower {
    public static final String POWER_ID = makeID(AtTheWellPower.class.getSimpleName());

    public AtTheWellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "GutsPower");
        }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new GutsPower(owner, 3)));
        addToBot(new VFXAction(new LightningEffect(owner.hb.cX, owner.hb.cY)));
        addToBot(new LoseHPAction(owner, owner, 99999));
        addToBot(new FgoNpAction(80));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
