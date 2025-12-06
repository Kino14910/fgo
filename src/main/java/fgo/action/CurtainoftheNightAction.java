package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CurtainoftheNightAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
    private final AbstractPlayer player;
    private final int numberOfCards;
    private final boolean upgraded;
    public CurtainoftheNightAction(int numberOfCards, boolean upgraded) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            if (player.discardPile.isEmpty() || numberOfCards <= 0) {
                isDone = true;
                return;
            }
            boolean optional = false;
            if (player.discardPile.size() <= numberOfCards && !optional) {
                ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
                for (AbstractCard c : player.discardPile.group) {
                    cardsToMove.add(c);
                    if (upgraded) {
                        c.upgrade();
                    }
                }
                for (AbstractCard c : cardsToMove) {
                    if (player.hand.size() < 10) {
                        player.hand.addToHand(c);
                        player.discardPile.removeCard(c);
                    }
                    c.lighten(false);
                }
                isDone = true;
                return;
            }
            if (numberOfCards == 1) {
                AbstractDungeon.gridSelectScreen.open(player.discardPile, numberOfCards, TEXT[0], false);
            } else {
                AbstractDungeon.gridSelectScreen.open(player.discardPile, numberOfCards, TEXT[1] + numberOfCards + TEXT[2], false);
            }

            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (player.hand.size() < 10) {
                    player.hand.addToHand(c);
                    if (upgraded) {
                        c.upgrade();
                    }
                    player.discardPile.removeCard(c);
                }
                c.lighten(false);
                c.unhover();
            }
            for (AbstractCard c : player.discardPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }

}
