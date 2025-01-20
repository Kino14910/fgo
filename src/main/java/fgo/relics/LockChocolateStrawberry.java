package fgo.relics;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fgo.cards.colorless.ignore.BlackBarrel;
import fgo.cards.colorless.ignore.WallOfSnowflakes;
import fgo.patches.Enum.FGOCardColor;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;
import static fgo.FGOMod.makeID;

public class LockChocolateStrawberry extends BaseRelic {
    private static final String NAME = "LockChocolateStrawberry";
	public static final String ID = makeID(NAME);
    public LockChocolateStrawberry() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.MAGICAL);
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
