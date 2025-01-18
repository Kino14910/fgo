package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MidsummerNightDream extends CustomRelic {
    public static final String ID = "MidsummerNightDream";
    private static final String IMG = "fgo/images/relics/MidsummerNightDream.png";
    private static final String IMG_OTL = "fgo/images/relics/outline/MidsummerNightDream.png";
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public MidsummerNightDream() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MidsummerNightDream();
    }
}
