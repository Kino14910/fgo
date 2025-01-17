package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BowInsignia extends CustomRelic {
    public static final String ID = "BowInsignia";
    private static final String IMG = "fgo/images/relics_Master/BowInsignia.png";
    private static final String IMG_OTL = "fgo/images/relics_Master/outline/BowInsignia.png";
    public BowInsignia() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BowInsignia();
    }
}
