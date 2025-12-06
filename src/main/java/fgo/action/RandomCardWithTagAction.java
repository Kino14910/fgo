package fgo.action;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRng;

import java.util.ArrayList;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class RandomCardWithTagAction extends AbstractGameAction {
    public boolean upgradeCard;
    public AbstractCard.CardTags tag;
    public boolean free;
    public boolean oneless;

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch) {
        tag = tagToSearch;
        actionType = ActionType.CARD_MANIPULATION;
    }

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch, boolean free) {
        tag = tagToSearch;
        free = free;
        oneless = false;
    }

    public RandomCardWithTagAction(boolean upgraded, AbstractCard.CardTags tagToSearch, boolean free, boolean oneless) {
        upgradeCard = upgraded;
        tag = tagToSearch;
        free = free;
        oneless = oneless;
    }

    @Override
    public void update() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> stringAbstractCardEntry : CardLibrary.cards.entrySet()) {
            if (stringAbstractCardEntry.getValue().hasTag(tag)) {
                tmp.add(stringAbstractCardEntry.getKey());
            }
        }

        AbstractCard cStudy = CardLibrary.cards.get(tmp.get(cardRng.random(0, tmp.size() - 1))).makeCopy();

        if (upgradeCard) {
            cStudy.upgrade();
        }

        if (free) {
            cStudy.freeToPlayOnce = true;
        }

        if (oneless) {
            cStudy.modifyCostForCombat(-1);
        }

        addToBot(new MakeTempCardInHandAction(cStudy, true));

        isDone = true;
    }
}