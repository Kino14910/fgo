package fgo.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class BlessRockChoco extends BaseRelic {
    private static final String NAME = "BlessRockChoco";
	public static final String ID = makeID(NAME);
    public BlessRockChoco() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.costForTurn >= 3) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            drawnCard.costForTurn -= 1;
            drawnCard.isCostModifiedForTurn = true;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlessRockChoco();
    }
}
