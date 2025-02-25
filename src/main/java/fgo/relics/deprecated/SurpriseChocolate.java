package fgo.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.action.deprecated.StarBasketAction;
import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

import static fgo.FGOMod.makeID;

public class SurpriseChocolate extends BaseRelic {
    private static final String NAME = "SurpriseChocolate";
	public static final String ID = makeID(NAME);
    public SurpriseChocolate() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new StarBasketAction());
        this.grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SurpriseChocolate();
    }
}
