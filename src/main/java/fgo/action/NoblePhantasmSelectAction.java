package fgo.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.NPOverChargePower;
import fgo.powers.NoblePhantasmCardPower;
import fgo.ui.panels.NobleDeckCards;
import fgo.utils.NobleCardGroup;

public class NoblePhantasmSelectAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    private int OCAmt;
    public NoblePhantasmSelectAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            NobleCardGroup<AbsNoblePhantasmCard> nobleCardGroup = new NobleCardGroup<AbsNoblePhantasmCard>();
            if (NobleDeckCards.nobleCards.group.isEmpty()) {
                isDone = true;
                return;
            }

            for (AbstractCard card : NobleDeckCards.nobleCards.group) {
                AbsNoblePhantasmCard cardCopy = (AbsNoblePhantasmCard)card.makeCopy();
                nobleCardGroup.addToBottom(cardCopy);
                UnlockTracker.markCardAsSeen(cardCopy.cardID);
            }
            
            AbstractPlayer p = AbstractDungeon.player;
            String OC = NPOverChargePower.POWER_ID;
            int OCAmt = p.hasPower(OC) ? p.getPower(OC).amount : 0;

            if (OCAmt > 0) {
                nobleCardGroup.group.forEach(card -> {
                    if (card instanceof AbsNoblePhantasmCard) {
                        for (int i = 0; i < OCAmt; i++) {
                            ((AbsNoblePhantasmCard) card).upgrade();
                        }
                    }
                });
            }

            if (p.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                nobleCardGroup.addToTop(NoblePhantasmCardPower.card);
            }
            
            AbstractDungeon.gridSelectScreen.open(nobleCardGroup, 1, NPTEXT[2], false, false, true, false);
            tickDuration();
            return;
        }

        ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
        if (!selectedCards.isEmpty()) {
            AbsNoblePhantasmCard selectedCard = (AbsNoblePhantasmCard)selectedCards.get(0);
            AbsNoblePhantasmCard selectedCardCopy = (AbsNoblePhantasmCard)selectedCard.makeCopy();
            if (OCAmt > 0) {
                for (int i = 0; i < OCAmt; i++) {
                    selectedCardCopy.upgrade();
                }
            }
            addToBot(new MakeTempCardInHandAction(selectedCardCopy));
            selectedCards.clear();
        }
        tickDuration();
    }
}
