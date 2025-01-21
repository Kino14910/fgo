//package fgo.powers.deprecated;
//
//import basemod.helpers.CardModifierManager;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.CardHelper;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import fgo.powers.StarGainPower;
//
//import java.util.Objects;
//
//@Deprecated
//
import static fgo.FGOMod.makeID;

//public class QuickPerformancePower extends BasePower {
//    public static final String POWER_ID = "QuickPerformancePower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    public QuickPerformancePower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/QuickPerformancePower.png";
//        String path48 = "fgo/images/powers/QuickPerformancePower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//    }
//
//    @Override
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (Objects.equals(card.glowColor, CardHelper.getColor(22, 88, 11)) || CardModifierManager.hasModifier(card, "InfestQuick")) {
//            this.flash();
//            if (this.owner.hasPower("StarRatePower")) {
//                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StarGainPower(this.owner, this.amount * 2), this.amount * 2));
//            } else {
//                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StarGainPower(this.owner, this.amount), this.amount));
//            }
//        }
//    }
//
//    public AbstractPower makeCopy() {
//        return new QuickPerformancePower(this.owner, this.amount);
//    }
//}
