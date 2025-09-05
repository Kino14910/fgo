package fgo.ui.panels;

import java.util.ArrayList;
import java.util.Collections;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.abstracts.CustomSavable;
import fgo.cards.noblecards.BeautifulJourney;
import fgo.cards.noblecards.EternalMemories;
import fgo.cards.noblecards.IraLupus;
import fgo.characters.Master;

public class NobleDeckCards implements CustomSavable<ArrayList<String>> {
    public static ArrayList<String> cards;
    
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