package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class SpringOfFirePower extends BasePower {
    public static final String POWER_ID = makeID(SpringOfFirePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SpringOfFirePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "GutsTriggerPower");
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new NPDamagePower(this.owner, this.amount), this.amount));
        this.addToBot(new RemoveDebuffsAction(this.owner));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {
        return new SpringOfFirePower(this.owner, this.amount);
    }
}
