package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.relics.BaseRelic;

public class SaveStone extends BaseRelic {
    private static final String NAME = "SaveStone";
	public static final String ID = makeID(NAME);
    public SaveStone() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0], 3.5);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.player.gainGold((int) (AbstractDungeon.player.gold * 0.035F));
        grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        grayscale = false;
    }
}
