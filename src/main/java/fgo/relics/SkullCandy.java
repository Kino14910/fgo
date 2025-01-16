package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class SkullCandy extends CustomRelic {
    public static final String ID = "SkullCandy";
    private static final String IMG = "img/relics_Master/SkullCandy.png";
    private static final String IMG_OTL = "img/relics_Master/outline/SkullCandy.png";
    public SkullCandy() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        if (AbstractDungeon.player.hasRelic("HalloweenRoyalty")) {
            AbstractDungeon.player.getRelic("HalloweenRoyalty").onTrigger();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SkullCandy();
    }
}
