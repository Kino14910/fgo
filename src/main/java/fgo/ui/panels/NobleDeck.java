package fgo.ui.panels;

import static fgo.FGOMod.makeID;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;

import basemod.BaseMod;
import basemod.TopPanelItem;
import fgo.patches.Enum.CardTagsEnum;
import fgo.utils.FGOInputActionSet;
import fgo.utils.NobleCardGroup;

public class NobleDeck extends TopPanelItem {
    private static final Texture IMG = new Texture("fgo/images/ui/NobleTopPanel.png");
    public static final String ID = makeID(NobleDeck.class.getSimpleName());
    public static NobleCardGroup nobleCards = new NobleCardGroup();
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString(makeID(NobleDeck.class.getSimpleName()));
    public static final String[] TEXT = NobleDeck.tutorialStrings.TEXT;
    public static final String LABEL = TEXT[0];
    public static final String MSG = TEXT[1];
    public NobleDeck() {
        super(IMG, ID);
    }

    @Override
    public void update() {
        super.update();
        if (FGOInputActionSet.nobleDeckAction.isJustPressed()) {
            this.onClick();
        }
    }

    @Override
    protected void onClick() {
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

        if (!AbstractDungeon.isScreenUp) {
            BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);
            return;
        }

        switch (AbstractDungeon.screen) {
            case COMBAT_REWARD:
                AbstractDungeon.closeCurrentScreen();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                break;
            case DEATH:
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                AbstractDungeon.deathScreen.hide();
                break;
            case BOSS_REWARD:
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                AbstractDungeon.bossRelicScreen.hide();
                break;
            case SHOP:
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                break;
            case MAP:
                if (!AbstractDungeon.dungeonMapScreen.dismissable) {
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;

                } else {
                    if (AbstractDungeon.previousScreen != null) {
                        AbstractDungeon.screenSwap = true;
                    }
                    AbstractDungeon.closeCurrentScreen();

                }
                break;
            case SETTINGS:
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                break;
            case INPUT_SETTINGS:
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                break;
            case CARD_REWARD:
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
                AbstractDungeon.dynamicBanner.hide();
                break;
            case GRID:
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
                AbstractDungeon.gridSelectScreen.hide();
                break;
            case HAND_SELECT:
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
                break;
            default:
                // 默认行为：关闭当前屏幕（如果有）再打开
                if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE) {
                    AbstractDungeon.closeCurrentScreen();
                }
                break;
        }

        BaseMod.openCustomScreen(NobleDeckViewScreen.Enum.Noble_Phantasm, nobleCards);


        // // 标记卡牌为已见
        // nobleCards.group.forEach(c -> UnlockTracker.markCardAsSeen(c.cardID));
    }

    @Override
    protected void onHover() {
        if (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == NobleDeckViewScreen.Enum.Noble_Phantasm) {
            TipHelper.renderGenericTip(1550.0f * Settings.scale, (float)Settings.HEIGHT - 120.0f * Settings.scale, LABEL + "(" + FGOInputActionSet.nobleDeckAction.getKeyString() + ")", MSG);
        }
        super.onHover();
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
