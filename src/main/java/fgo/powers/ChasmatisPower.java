package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class ChasmatisPower extends BasePower {
    public static final String POWER_ID = makeID(ChasmatisPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public ChasmatisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "OriginBulletPower");
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    /*@Override
    public void atStartOfTurnPostDraw() {
        if (this.owner.hasPower(CursePower.POWER_ID)) {
            int BulletAmt = 0;
            BulletAmt += (this.owner.getPower(CursePower.POWER_ID)).amount * this.amount;
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, BulletAmt), BulletAmt));
        }
    }*/

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == this.owner && target == this.owner && power.ID.equals(CursePower.POWER_ID)) {
            this.flash();
            this.addToBot(new DrawCardAction(this.owner, this.amount));
        }
    }

    public AbstractPower makeCopy() {return new ChasmatisPower(this.owner, this.amount);}
}
