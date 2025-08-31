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
            AbstractDungeon.screenSwap = false;
            if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                AbstractDungeon.previousScreen = null;
            }
            AbstractDungeon.closeCurrentScreen();
            CardCrawlGame.sound.play("DECK_CLOSE", 0.05f);
            return;
        }

        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.closeCurrentScreen();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        } else if (!AbstractDungeon.isScreenUp) {
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
            AbstractDungeon.deathScreen.hide();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
            AbstractDungeon.bossRelicScreen.hide();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            if (AbstractDungeon.previousScreen != null) {
                AbstractDungeon.screenSwap = true;
            }
            AbstractDungeon.closeCurrentScreen();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS) {
            if (AbstractDungeon.previousScreen != null) {
                AbstractDungeon.screenSwap = true;
            }
            AbstractDungeon.closeCurrentScreen();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
            AbstractDungeon.dynamicBanner.hide();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
            AbstractDungeon.gridSelectScreen.hide();
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        } else {
            // 默认行为：关闭当前屏幕（如果有）再打开
            if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE) {
                AbstractDungeon.closeCurrentScreen();
            }
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
        }

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

    public static void reset() {
        nobleCards.clear();
    }
}
