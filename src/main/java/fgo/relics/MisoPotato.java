package fgo.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class MisoPotato extends BaseRelic {
    private static final String NAME = "MisoPotato";
	public static final String ID = makeID(NAME);
    public MisoPotato() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MisoPotato();
    }
}
