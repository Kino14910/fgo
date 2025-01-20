//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.CardHelper;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import fgo.powers.ArtsPerformancePower;
//
//import java.util.Objects;
//
//@Deprecated
//
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

//public class ArtsResistancePower extends BasePower {
//    public static final String POWER_ID = "ArtsResistancePower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    public ArtsResistancePower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.DEBUFF;
//
//        String path128 = "img/powers_Master/ArtsResistancePower84.png";
//        String path48 = "img/powers_Master/ArtsResistancePower32.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        this.updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//    }
//
//    @Override
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (Objects.equals(card.glowColor, CardHelper.getColor(11, 44, 121))) {
//            this.flash();
//            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtsPerformancePower(AbstractDungeon.player, this.amount), this.amount, Settings.FAST_MODE));
//            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
//        }
//    }
//
//    public AbstractPower makeCopy() {
//        return new ArtsResistancePower(this.owner, this.amount);
//    }
//}
