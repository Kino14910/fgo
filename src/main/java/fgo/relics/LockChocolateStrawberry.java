package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import fgo.cards.colorless.mash.BlackBarrel;
import fgo.cards.colorless.mash.WallOfSnowflakes;
import fgo.characters.CustomEnums.FGOCardColor;

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
    public void onEnterRoom(AbstractRoom room) {
        AbstractCard cardById = AbstractDungeon.player.masterDeck.findCardById(WallOfSnowflakes.ID);
        if (AbstractDungeon.actNum > 1 && AbstractDungeon.floorNum % 17 == 0 && cardById != null) {
            WallOfSnowflakes card = (WallOfSnowflakes)cardById;
            card.timesUpgraded++;
            card.initializeDescription();
        }
    }
}
