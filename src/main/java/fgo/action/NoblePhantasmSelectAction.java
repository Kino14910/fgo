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
            CardGroup group = NobleDeck.nobleCards;
            if (AbstractDungeon.player.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                group.addToTop(new HollowHeartAlbion());
            } else {
                // for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                //     if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
                //         AbstractCard copy = card.makeCopy();
                //         if (card.upgraded) {
                //             copy.upgrade();
                //         } 
                //         group.addToTop(copy);
                //     }
                // }
                
                if (group.isEmpty()) {
                    this.isDone = true;
                    return;
                }
            }

            
            group.group.forEach(c -> UnlockTracker.markCardAsSeen(c.cardID));

            if (this.upgraded) {
                group.group.forEach(AbstractCard::upgrade);
            }

            AbstractDungeon.gridSelectScreen.open(group, 1, NPTEXT[2], false, false, true, false);
            this.tickDuration();
            return;
        }

        ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
        if (!selectedCards.isEmpty()) {
            AbstractCard selectedCard = selectedCards.get(0);
            AbstractCard copy = selectedCard.makeCopy();
            if (selectedCard.upgraded || this.upgraded) {
                copy.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy, this.amount));
            selectedCards.clear();
        }
        this.tickDuration();
    }
}
