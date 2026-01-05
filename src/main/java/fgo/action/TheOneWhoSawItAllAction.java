package fgo.action;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TheOneWhoSawItAllAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard.CardType typeToCheck;
    private final int damage;
    public TheOneWhoSawItAllAction(int amount, AbstractCard.CardType type, int damage) {
        p = AbstractDungeon.player;
        setValues(p, p, amount);
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
        typeToCheck = type;
        this.damage = damage;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            if (p.drawPile.isEmpty()) {
                isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator<AbstractCard> var2 = p.drawPile.group.iterator();

            AbstractCard card;
            while(var2.hasNext()) {
                card = var2.next();
                if (card.type == typeToCheck) {
                    tmp.addToRandomSpot(card);
                }
            }

            if (tmp.isEmpty()) {
                isDone = true;
                return;
            }

            for(int i = 0; i < amount; ++i) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    if (p.hand.size() == 10) {
                        p.drawPile.moveToDiscardPile(card);
                        p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0f);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        p.drawPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }

                    card.baseDamage += damage;
                    card.applyPowers();
                    card.flash();
                }
            }

            isDone = true;
        }

        tickDuration();
    }
}
