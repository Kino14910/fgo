package fgo.ui.panels;

import java.util.ArrayList;
import java.util.Arrays;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import basemod.abstracts.CustomSavable;
import fgo.cards.noblecards.AroundCaliburn;
import fgo.cards.noblecards.BeautifulJourney;
import fgo.cards.noblecards.IraLupus;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.characters.Master;
import fgo.utils.NobleCardGroup;

public class NobleDeckCards implements CustomSavable<ArrayList<String>> {
    public static ArrayList<String> cards;
    public static NobleCardGroup nobleCards;

    public static NobleCardGroup getNobleCards() {
        if (nobleCards == null) {
            nobleCards = new NobleCardGroup();
        }
        return nobleCards;
    }
    
    @Override
    public ArrayList<String> onSave() {
        return (AbstractDungeon.player instanceof Master && NobleDeckCards.cards != null) ? new ArrayList<String>(NobleDeckCards.cards) : null;
    }
 
    @Override
    public void onLoad(ArrayList<String> save) {
        if (AbstractDungeon.player instanceof Master && (save == null || save.isEmpty())) {
            // no saved data -> use defaults
            NobleDeckCards.reset();
            return;
        }

        NobleDeckCards.cards = save;
    }

    public static void reset() {
        NobleDeckCards.cards = new ArrayList<>(Arrays.asList(
            //宝具卡
            AroundCaliburn.ID,
            BeautifulJourney.ID,            
            IraLupus.ID
        ));
        if (nobleCards != null) {
            nobleCards.clear();
        }
        NobleDeckCards.addCards(NobleDeckCards.cards);
    }

    public static void addCard(String cardId) {
        if (NobleDeckCards.cards == null) {
            NobleDeckCards.cards = new ArrayList<String>();
        }
        NobleDeckCards.cards.add(cardId);
        addCardById(cardId);
    }

    public static void addCard(AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            getNobleCards().addToTop(card);
        }
    }

    public static void addCardById(String cardId) {
        AbstractCard card = CardLibrary.getCopy(cardId);
        if (card != null && card.color == FGOCardColor.NOBLE_PHANTASM) {
            getNobleCards().addToTop(card);
        }
    }

    public static void addCards(ArrayList<String> cards) {
        for (String cardId : cards) {
            addCardById(cardId);
        }
    }
}