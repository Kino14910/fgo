package fgo.action;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import fgo.cards.tempCards.SupportCraft;
import fgo.characters.CustomEnums.FGOCardColor;

public class BlessedScionAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BlessedScionAction.class.getSimpleName()));
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    public BlessedScionAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        p = (AbstractPlayer)target;
    }

    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (p.hand.size() == 1) {
                AbstractCard card = processCard(p.hand.getBottomCard());
                this.addToTop(new MakeTempCardInDrawPileAction(card, amount, false, true));
                p.hand.moveToExhaustPile(card);
                this.isDone = true;
                return;
            }

            // 处理手牌数量大于1的情况
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
            this.tickDuration();
            return;
        }


        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard selectedCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
            AbstractCard processedCard = processCard(selectedCard);
            this.addToTop(new MakeTempCardInDrawPileAction(processedCard, amount, false, true));
            AbstractDungeon.player.hand.addToHand(processedCard);
            p.hand.moveToExhaustPile(selectedCard);
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    private AbstractCard processCard(AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            card = new SupportCraft();
        }
        if (card.costForTurn >= 0) {
            card.freeToPlayOnce = true;
        }
        return card;
    }
}
