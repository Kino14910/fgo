package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DragonCoreAction extends AbstractGameAction {
    public DragonCoreAction() {
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                cardsToExhaust.add(c);
            }
        }

        for (AbstractCard ignored : cardsToExhaust) {
            AbstractCard cm = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            cm.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(cm, true));
        }

        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

        isDone = true;
    }
}
