package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

public class SparksRouteAction extends AbstractGameAction {
    public static final String[] MSG;
    public static final String[] LABEL;
    private static final TutorialStrings tutorialStrings;

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Shuffle Tip");
        MSG = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
    }

    private boolean shuffled = false;

    public SparksRouteAction(boolean sparksRoutePlus) {
        setValues(null, null, 0);
        actionType = ActionType.SHUFFLE;
        if (!(Boolean) TipTracker.tips.get("SHUFFLE_TIP")) {
            AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.SHUFFLE);
            TipTracker.neverShowAgain("SHUFFLE_TIP");
        }
    }

    @Override
    public void update() {
        if (!shuffled) {
            shuffled = true;
            AbstractPlayer p = AbstractDungeon.player;
            int theSize = p.hand.size();
            addToBot(new ArmamentsAction(true));
            addToBot(new PutOnDeckAction(p, p, theSize, true));
            addToBot(new ShuffleAction(p.drawPile));
            addToBot(new DrawCardAction(p, theSize));
        }

        isDone = true;
    }
}
