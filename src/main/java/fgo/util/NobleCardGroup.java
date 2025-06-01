package fgo.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import fgo.patches.Enum.CardTagsEnum;

public class NobleCardGroup extends CardGroup {
    public NobleCardGroup() {
        super(CardGroupType.UNSPECIFIED);
    }

    @Override
    public void addToTop(AbstractCard card) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            super.addToTop(card);
        }
    }

    @Override
    public void addToBottom(AbstractCard card) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            super.addToBottom(card);
        }
    }

    public void addCard(AbstractCard card) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            this.addToTop(card.makeCopy());
        }
    }
}
