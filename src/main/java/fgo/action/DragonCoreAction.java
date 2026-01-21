package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DragonCoreAction extends AbstractGameAction {
    public DragonCoreAction() {
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        CardGroup hand = AbstractDungeon.player.hand;
        for (AbstractCard c : hand.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                cardsToExhaust.add(c);
            }
        }

        for (AbstractCard card : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(card, hand));
            AbstractCard randCard = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            randCard.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(randCard, true));
        }

        isDone = true;
    }
}
