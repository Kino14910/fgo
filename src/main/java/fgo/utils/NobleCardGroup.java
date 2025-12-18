package fgo.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.characters.CustomEnums.FGOCardColor;

public class NobleCardGroup<T extends AbsNoblePhantasmCard> extends CardGroup {
    public NobleCardGroup() {
        super(CardGroupType.UNSPECIFIED);
    }

    @Override
    public void addToTop(AbstractCard card) {
        if (card instanceof AbsNoblePhantasmCard && card.color == FGOCardColor.NOBLE_PHANTASM) {
            super.addToTop(card);
        }
    }

    @Override
    public void addToBottom(AbstractCard card) {
        if (card instanceof AbsNoblePhantasmCard && card.color == FGOCardColor.NOBLE_PHANTASM) {
            super.addToBottom(card);
        }
    }

    public void addCard(AbsNoblePhantasmCard card) {
        if (card instanceof AbsNoblePhantasmCard && card.color == FGOCardColor.NOBLE_PHANTASM) {
            addToTop(card);
        }
    }
}
