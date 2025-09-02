package fgo.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import fgo.characters.CustomEnums.FGOCardColor;

public class NobleCardGroup extends CardGroup {
    public NobleCardGroup() {
        super(CardGroupType.UNSPECIFIED);
    }

    @Override
    public void addToTop(AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            super.addToTop(card);
        }
    }

    @Override
    public void addToBottom(AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            super.addToBottom(card);
        }
    }

    public void addCard(AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            this.addToTop(card.makeCopy());
        }
    }
}
