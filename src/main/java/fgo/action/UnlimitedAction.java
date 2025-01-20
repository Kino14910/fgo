package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

@Deprecated
public class UnlimitedAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    public UnlimitedAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    group.addToBottom(c);
                }
            }

            for (AbstractCard c : group.group) {
                UnlockTracker.markCardAsSeen(c.cardID);
            }

            AbstractDungeon.gridSelectScreen.open(group, 1, NPTEXT[3], false, false, true, false);
            this.tickDuration();
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard cStudy = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy, 1));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            this.tickDuration();
        }
    }

}
