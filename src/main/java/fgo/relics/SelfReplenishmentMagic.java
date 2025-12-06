package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.action.FgoNpAction;
import fgo.characters.CustomEnums.FGOCardColor;

public class SelfReplenishmentMagic extends BaseRelic {
    private static final String NAME = "SelfReplenishmentMagic";
	public static final String ID = makeID(NAME);
    public SelfReplenishmentMagic() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new FgoNpAction(6));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
