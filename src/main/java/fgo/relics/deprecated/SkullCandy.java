package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

public class SkullCandy extends BaseRelic {
    private static final String NAME = "SkullCandy";
	public static final String ID = makeID(NAME);
    public SkullCandy() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        if (AbstractDungeon.player.hasRelic(HalloweenRoyalty.ID)) {
            AbstractDungeon.player.getRelic(HalloweenRoyalty.ID).onTrigger();
        }
    }
}
