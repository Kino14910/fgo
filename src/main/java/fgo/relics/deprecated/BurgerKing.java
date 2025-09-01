package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

// import fgo.cards.curses.Dumuzid;
import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

public class BurgerKing extends BaseRelic {
    private static final String NAME = "BurgerKing";
	public static final String ID = makeID(NAME);
    public BurgerKing() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(40, true);
        // AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Dumuzid(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("Dumuzid");
    }
}
