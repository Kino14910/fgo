package fgo.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class MidsummerNightDream extends BaseRelic {
    private static final String NAME = "MidsummerNightDream";
	public static final String ID = makeID(NAME);
    public MidsummerNightDream() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.FLAT);
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
