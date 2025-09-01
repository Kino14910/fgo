package fgo.utils;

import java.util.Iterator;

import com.megacrit.cardcrawl.cards.AbstractCard;

import fgo.panel.NobleDeck;

public class NobleCardHelper {
    public static boolean hasCardWithID(String targetID) {
        Iterator var1 = NobleDeck.nobleCards.group.iterator();

        AbstractCard c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (AbstractCard)var1.next();
        } while(!c.cardID.equals(targetID));

        return true;
    }
}