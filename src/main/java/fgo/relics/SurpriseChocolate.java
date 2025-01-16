//package fgo.relics;
//
//import fgo.action.StarBasketAction;
//import basemod.abstracts.CustomRelic;
//import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//
//public class SurpriseChocolate extends CustomRelic {
//    public static final String ID = "SurpriseChocolate";
//    private static final String IMG = "img/relics_Master/SurpriseChocolate.png";
//    private static final String IMG_OTL = "img/relics_Master/outline/SurpriseChocolate.png";
//
//    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
//    public SurpriseChocolate() {
//        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.CLINK);
//    }
//
//    @Override
//    public void atBattleStart() {
//        this.flash();
//        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new StarBasketAction());
//        this.grayscale = true;
//    }
//
//    @Override
//    public void justEnteredRoom(AbstractRoom room) {
//        this.grayscale = false;
//    }
//
//    @Override
//    public String getUpdatedDescription() {
//        return this.DESCRIPTIONS[0];
//    }
//
//    @Override
//    public AbstractRelic makeCopy() {
//        return new SurpriseChocolate();
//    }
//}
