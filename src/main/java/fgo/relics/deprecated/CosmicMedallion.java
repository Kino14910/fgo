package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.relics.BaseRelic;

public class CosmicMedallion extends BaseRelic {
    private static final String NAME = "CosmicMedallion";
	public static final String ID = makeID(NAME);
    public CosmicMedallion() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlurPower(AbstractDungeon.player, 3), 3));
        grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
