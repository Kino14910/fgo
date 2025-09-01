package fgo.relics;

import static fgo.FGOMod.makeID;

import fgo.patches.Enum.FGOCardColor;

public class BowInsignia extends BaseRelic {
    private static final String NAME = "BowInsignia";
	public static final String ID = makeID(NAME);
    public BowInsignia() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
