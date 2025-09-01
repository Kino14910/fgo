package fgo.relics;

import static fgo.FGOMod.makeID;

import fgo.patches.Enum.FGOCardColor;

public class MidsummerNightDream extends BaseRelic {
    private static final String NAME = "MidsummerNightDream";
	public static final String ID = makeID(NAME);
    public MidsummerNightDream() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
