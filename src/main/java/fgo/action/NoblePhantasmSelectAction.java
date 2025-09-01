package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fgo.cards.noblecards.HollowHeartAlbion;
import fgo.panel.NobleDeck;
import fgo.powers.NoblePhantasmCardPower;

public class NoblePhantasmSelectAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    private final boolean upgraded;
    public NoblePhantasmSelectAction(boolean upgraded, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            // CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            CardGroup nobleCardGroup = NobleDeck.nobleCards;
            if (nobleCardGroup.isEmpty()) {
                this.isDone = true;
                return;
            }

            nobleCardGroup.group.forEach(c -> UnlockTracker.markCardAsSeen(c.cardID));

            if (this.upgraded) {
                nobleCardGroup.group.forEach(AbstractCard::upgrade);
            }
            //  else {
            //     for (AbstractCard card : group.group) {
            //         if (card.upgraded) {
            //             card.upgrade();
            //         }
            //     }
            // }

            if (AbstractDungeon.player.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                nobleCardGroup.addToTop(new HollowHeartAlbion());
            }
            
            AbstractDungeon.gridSelectScreen.open(nobleCardGroup, 1, NPTEXT[2], false, false, true, false);
            this.tickDuration();
            return;
        }

        ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
        if (!selectedCards.isEmpty()) {
            AbstractCard selectedCard = selectedCards.get(0);
            AbstractCard selectedCardCopy = selectedCard.makeCopy();
            if (selectedCard.upgraded || this.upgraded) {
                selectedCardCopy.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(selectedCardCopy, this.amount));
            selectedCards.clear();
        }
        this.tickDuration();
    }
}
