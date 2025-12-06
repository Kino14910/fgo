package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NightlessCharismaAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard.CardType typeToCheck;

    public NightlessCharismaAction(int amount, AbstractCard.CardType type) {
        p = AbstractDungeon.player;
        setValues(p, p, amount);
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
        typeToCheck = type;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            if (p.drawPile.isEmpty()) {
                isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.drawPile.group) {
                if (c.type == typeToCheck)
                    tmp.addToRandomSpot(c);
            }

            if (tmp.isEmpty()) {
                isDone = true;
                return;
            }

            for (int i = 0; i < amount; ++i) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    AbstractCard card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    if (p.hand.size() == 10) {
                        p.drawPile.moveToDiscardPile(card);
                        p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        p.drawPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }

                    if (card.canUpgrade()) {
                        card.upgrade();
                        card.superFlash();
                        card.applyPowers();
                    }
                }
            }

            isDone = true;
        }

        tickDuration();
    }
}
