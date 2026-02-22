package fgo.dynamicvariables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;
import fgo.cards.FGOCard;

public class CriticalStarVariable extends DynamicVariable {
    @Override
    public String key() {
        return "Star";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.isModified;
        }
        return false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            fgoCard.isModified = true;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.star;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.baseStar;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.upgradeStar;
        }
        return false;
    }
}
