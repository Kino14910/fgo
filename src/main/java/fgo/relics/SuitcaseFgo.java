package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class SuitcaseFgo extends CustomRelic {
    public static final String ID = "SuitcaseFgo";
    private static final String IMG = "fgo/images/relics_Master/SuitcaseFgo.png";
    private static final String IMG_OTL = "fgo/images/relics_Master/outline/SuitcaseFgo.png";
    public SuitcaseFgo() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SuitcaseFgo();
    }
}
