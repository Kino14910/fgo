package fgo.powers;

import fgo.cards.noblecards.KurKigalIrkalla;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BlessingOfKurPower extends AbstractPower {
    public static final String POWER_ID = "BlessingOfKurPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    public BlessingOfKurPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/BlessingOfKurPower84.png";
        String path48 = "fgo/images/powers_Master/BlessingOfKurPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {this.description = DESCRIPTIONS[0];}

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cardID.equals(KurKigalIrkalla.ID)) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 3), 3));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, 3), 3));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    public AbstractPower makeCopy() {
        return new BlessingOfKurPower(this.owner);
    }
}
