package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GutsPower extends AbstractPower {
    public static final String POWER_ID = "GutsPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int time;
    public GutsPower(AbstractCreature owner, int amount, int time) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.time = time;
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/GutsPower84.png";
        String path48 = "fgo/images/powers_Master/GutsPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new HealAction(this.owner, this.owner, this.amount));
        --this.time;
        if (this.time == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        this.updateDescription();
    }

    @Override
    public void onRemove() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, BriefStrengthPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        if (this.time == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.amount);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.time);
        }
    }

    public AbstractPower makeCopy() {
        return new GutsPower(this.owner, this.amount, time);
    }
}