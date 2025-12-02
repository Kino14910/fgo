package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class AtTheWellPower extends BasePower {
    public static final String POWER_ID = makeID(AtTheWellPower.class.getSimpleName());

    public AtTheWellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "GutsPower");
        }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if (!this.owner.hasPower(GutsPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new GutsPower(this.owner, this.amount)));
        }
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new GutsAtTheWellPower(this.owner, 3), 3));
        this.addToBot(new VFXAction(new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
        this.addToBot(new LoseHPAction(this.owner, this.owner, 99999));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    
}
