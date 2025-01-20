package fgo.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

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

    @Override
    public AbstractRelic makeCopy() {
        return new BowInsignia();
    }
}
