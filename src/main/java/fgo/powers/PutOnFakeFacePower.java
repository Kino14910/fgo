package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PutOnFakeFacePower extends AbstractPower {
    public static final String POWER_ID = "PutOnFakeFacePower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public PutOnFakeFacePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "fgo/images/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        int roll = MathUtils.random(2);
        if (roll == 0) {
            this.addToBot(new GainEnergyAction(1));
        } else if (roll == 1) {
            this.addToBot(new HealAction(this.owner, this.owner, 5));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "CursePower", 3));
        }
    }

    public AbstractPower makeCopy() {return new PutOnFakeFacePower(this.owner);}
}
