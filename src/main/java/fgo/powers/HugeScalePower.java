package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class HugeScalePower extends BasePower {
    public static final String POWER_ID = makeID(HugeScalePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HugeScalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        String path128 = "img/powers_Master/BuffRegenPower84.png";
        String path48 = "img/powers_Master/BuffRegenPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

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

    public AbstractPower makeCopy() {
        return new HugeScalePower(this.owner, this.amount);
    }
}
