package fgo.relics;

import basemod.abstracts.CustomRelic;
import fgo.cards.colorless.ignore.BlackBarrel;
import fgo.cards.colorless.ignore.WallOfSnowflakes;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class LockChocolateStrawberry extends CustomRelic {
    public static final String ID = "LockChocolateStrawberry";
    private static final String IMG = "fgo/images/relics/LockChocolateStrawberry.png";
    private static final String IMG_OTL = "fgo/images/relics/outline/LockChocolateStrawberry.png";
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public LockChocolateStrawberry() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new WallOfSnowflakes(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("WallOfSnowflakes");
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BlackBarrel(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("BlackBarrel");
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LockChocolateStrawberry();
    }
}
