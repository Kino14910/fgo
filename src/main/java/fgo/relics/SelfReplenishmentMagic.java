package fgo.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.action.FgoNpAction;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class SelfReplenishmentMagic extends BaseRelic {
    private static final String NAME = "SelfReplenishmentMagic";
	public static final String ID = makeID(NAME);
    public SelfReplenishmentMagic() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new FgoNpAction(6));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SelfReplenishmentMagic();
    }
}
