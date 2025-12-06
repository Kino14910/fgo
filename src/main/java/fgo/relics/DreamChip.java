package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.characters.CustomEnums.FGOCardColor;

public class DreamChip extends BaseRelic {
    private static final String NAME = "DreamChip";
	public static final String ID = makeID(NAME);
    private static boolean usedThisCombat = false;
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public DreamChip() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
        pulse = true;
        beginPulse();
    }

    @Override
    public void onShuffle() {
        if (!usedThisCombat) {
            flash();
            pulse = false;
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new MakeTempCardInHandAction(new CalculatedGamble(), 1));
            usedThisCombat = true;
            grayscale = true;
        }

    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        grayscale = false;
    }

    @Override
    public void onVictory() {
        pulse = false;
    }
}
