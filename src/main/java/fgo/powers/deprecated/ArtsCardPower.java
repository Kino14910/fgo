//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import fgo.action.FgoNpAction;
//
//@Deprecated
//
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

//public class ArtsCardPower extends BasePower {
//    public static final String POWER_ID = "ArtsCardPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//
//    public ArtsCardPower(AbstractCreature owner) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/ArtsCardPower.png";
//        String path48 = "fgo/images/powers/ArtsCardPower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0];
//    }
//
//    @Override
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        this.flash();
//        this.addToBot(new FgoNpAction(2));
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }
//
//    public AbstractPower makeCopy() {
//        return new ArtsCardPower(this.owner);
//    }
//}
