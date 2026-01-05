package fgo.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;

import fgo.ui.panels.NobleDeckCards;

public class NobleCardHelper {
    public static boolean hasCardWithID(String targetID) {
        if (targetID == null) {
            return false;
        }
        for (AbstractCard card : NobleDeckCards.nobleCards.group) {
            if (targetID.equals(card.cardID)) {
                return true;
            }
        }
        return false;
    }
}