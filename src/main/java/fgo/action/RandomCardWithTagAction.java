package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;
import java.util.Map;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRng;

public class RandomCardWithTagAction extends AbstractGameAction {
    public boolean upgradeCard;
    public AbstractCard.CardTags tag;
    public boolean free;
    public boolean oneless;

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch) {
        this.tag = tagToSearch;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public RandomCardWithTagAction(AbstractCard.CardTags tagToSearch, boolean free) {
        this.tag = tagToSearch;
        this.free = free;
        this.oneless = false;
    }

    public RandomCardWithTagAction(boolean upgraded, AbstractCard.CardTags tagToSearch, boolean free, boolean oneless) {
        this.upgradeCard = upgraded;
        this.tag = tagToSearch;
        this.free = free;
        this.oneless = oneless;
    }

    @Override
    public void update() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, AbstractCard> stringAbstractCardEntry : CardLibrary.cards.entrySet()) {
            if (stringAbstractCardEntry.getValue().hasTag(this.tag)) {
                tmp.add(stringAbstractCardEntry.getKey());
            }
        }

        AbstractCard cStudy = CardLibrary.cards.get(tmp.get(cardRng.random(0, tmp.size() - 1))).makeCopy();

        if (this.upgradeCard) {
            cStudy.upgrade();
        }

        if (this.free) {
            cStudy.freeToPlayOnce = true;
        }

        if (this.oneless) {
            cStudy.modifyCostForCombat(-1);
        }

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy, true));

        this.isDone = true;
    }
}