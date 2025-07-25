package fgo.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import fgo.cards.FGOCard;

public class NoblePhantasmVariable extends DynamicVariable {
    @Override
    public String key() {
        return "NP";
        // 在本地化文件中用于显示你的值的键。例如，!myKey!。
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
            return fgoCard.np;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.baseNP;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof FGOCard) {
            FGOCard fgoCard = (FGOCard) card;
            return fgoCard.upgradeNP;
        }
        return false;
    }
}
