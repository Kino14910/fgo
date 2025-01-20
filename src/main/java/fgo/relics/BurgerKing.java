package fgo.relics;

import basemod.abstracts.CustomRelic;
import fgo.cards.curse.Dumuzid;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

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
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Dumuzid(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("Dumuzid");
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BurgerKing();
    }
}
