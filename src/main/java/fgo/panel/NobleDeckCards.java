package fgo.panel;

import java.util.ArrayList;
import java.util.Collections;

import basemod.abstracts.CustomSavable;
import fgo.cards.noblecards.BeautifulJourney;
import fgo.cards.noblecards.EternalMemories;
import fgo.cards.noblecards.IraLupus;

public class NobleDeckCards implements CustomSavable<ArrayList<String>> {
    public static ArrayList<String> cards;
    
    @Override
    public ArrayList<String> onSave() {
        return new ArrayList<String>(NobleDeckCards.cards);
    }

    @Override
    public void onLoad(ArrayList<String> save) {
        if (save == null || save.isEmpty()) {
            // no saved data -> use defaults
            NobleDeckCards.reset();
            return;
        }

        NobleDeckCards.cards = save;
    }

    public static void reset() {
        NobleDeckCards.cards = new ArrayList<>();
        Collections.addAll(NobleDeckCards.cards, 
            //宝具卡
            EternalMemories.ID,
            BeautifulJourney.ID,            
            IraLupus.ID);
    }

    public static void addCard(String cardId) {
        if (NobleDeckCards.cards == null) {
            NobleDeckCards.cards = new ArrayList<String>();
        }
        NobleDeckCards.cards.add(cardId);
        NobleDeck.addCardById(cardId);
    }
}