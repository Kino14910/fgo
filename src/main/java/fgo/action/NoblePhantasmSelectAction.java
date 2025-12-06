package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import fgo.cards.noblecards.HollowHeartAlbion;
import fgo.powers.NoblePhantasmCardPower;
import fgo.ui.panels.NobleDeckCards;
import fgo.utils.NobleCardGroup;

public class NoblePhantasmSelectAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    private final boolean upgraded;
    public NoblePhantasmSelectAction(boolean upgraded, int amount) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            // CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            NobleCardGroup nobleCardGroup = new NobleCardGroup();
            if (NobleDeckCards.nobleCards.group.isEmpty()) {
                isDone = true;
                return;
            }

            for (AbstractCard card : NobleDeckCards.nobleCards.group) {
                AbstractCard cardCopy = card.makeCopy();
                nobleCardGroup.addToBottom(cardCopy);
                UnlockTracker.markCardAsSeen(cardCopy.cardID);
            }

            if (upgraded) {
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
            tickDuration();
            return;
        }

        ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
        if (!selectedCards.isEmpty()) {
            AbstractCard selectedCard = selectedCards.get(0);
            AbstractCard selectedCardCopy = selectedCard.makeCopy();
            if (selectedCard.upgraded || upgraded) {
                selectedCardCopy.upgrade();
            }
            addToBot(new MakeTempCardInHandAction(selectedCardCopy, amount));
            selectedCards.clear();
        }
        tickDuration();
    }
}
