package fgo.relics.deprecated;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

import static fgo.FGOMod.makeID;

public class SaveStone extends BaseRelic {
    private static final String NAME = "SaveStone";
	public static final String ID = makeID(NAME);
    public SaveStone() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3.5 + this.DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.player.gainGold((int) (AbstractDungeon.player.gold * 0.035F));
        this.grayscale = true;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SaveStone();
    }
}
