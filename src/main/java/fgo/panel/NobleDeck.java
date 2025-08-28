package fgo.panel;

import static fgo.FGOMod.makeID;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import basemod.BaseMod;
import basemod.TopPanelItem;
import fgo.patches.Enum.CardTagsEnum;
import fgo.util.NobleCardGroup;

public class NobleDeck extends TopPanelItem {
    private static final Texture IMG = new Texture("fgo/images/ui/NobleTopPanel.png");
    public static final String ID = makeID(NobleDeck.class.getSimpleName());
    public static NobleCardGroup nobleCards = new NobleCardGroup();
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;

    public NobleDeck() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        // 快速保护
        if (nobleCards.isEmpty()) {
            return;
        }

        // 如果 Noble 屏幕已经打开，则关闭它（切换行为）
        if (AbstractDungeon.screen == NobleDeckViewScreen.Enum.Noble_Phantasm) {
            AbstractDungeon.closeCurrentScreen();
            CardCrawlGame.sound.play("DECK_CLOSE", 0.05f);
            return;
        }

        // 否则，先关闭其他屏幕以避免冲突，然后打开我们的自定义屏幕并传入 nobleCards
        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE) {
            AbstractDungeon.closeCurrentScreen();
        }
        BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);

        // // 标记卡牌为已见
        // nobleCards.group.forEach(c -> UnlockTracker.markCardAsSeen(c.cardID));
    }

    public static void addCard(AbstractCard card) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            nobleCards.addToTop(card);
        }
    }

    public static void addCardById(String cardId) {
        AbstractCard card = CardLibrary.getCopy(cardId);
        if (card != null && card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            nobleCards.addToTop(card);
        }
    }

    public static void addCards(ArrayList<String> cards) {
        for (String cardId : cards) {
            addCardById(cardId);
        }
    }
}
