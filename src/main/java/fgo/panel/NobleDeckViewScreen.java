package fgo.panel;

import static fgo.FGOMod.makeID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import basemod.abstracts.CustomScreen;
import fgo.utils.NobleCardGroup;

public class NobleDeckViewScreen extends CustomScreen implements ScrollBarListener {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID(NobleDeckViewScreen.class.getSimpleName()));
    public static final String[] TEXT = NobleDeckViewScreen.uiStrings.TEXT;
    private static final String HEADER_INFO = TEXT[0];
    public static class Enum {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen Noble_Phantasm;
    }

    @Override
    public AbstractDungeon.CurrentScreen curScreen() {
        return Enum.Noble_Phantasm;
    }

    NobleCardGroup nobleCards;
    private static float drawStartX;
    private static float drawStartY;
    private static float padX;
    private static float padY;
    private static final float SCROLL_BAR_THRESHOLD = 500.0f * Settings.scale;
    private static final int CARDS_PER_LINE = 5;

    // 交互相关字段
    private AbstractCard hoveredCard = null;
    private AbstractCard clickStartedCard = null;
    private boolean grabbedScreen = false;
    private float grabStartY = 0.0f;
    private float currentDiffY = 0.0f;
    private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
    private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;

    // UI 元素
    private ScrollBar scrollBar;

    Logger logger = new Logger(NobleDeckViewScreen.class.getName());

    public NobleDeckViewScreen() {
        // 初始化UI布局
        drawStartX = Settings.WIDTH;
        drawStartX -= 5.0f * AbstractCard.IMG_WIDTH * 0.75f;
        drawStartX -= 4.0f * Settings.CARD_VIEW_PAD_X;
        drawStartX /= 2.0f;
        drawStartX += AbstractCard.IMG_WIDTH * 0.75f / 2.0f;
        
        // 设置起始Y坐标（与MasterDeckViewScreen一致）
        drawStartY = (float)Settings.HEIGHT * 0.66f;
        
        padX = AbstractCard.IMG_WIDTH * 0.75f + Settings.CARD_VIEW_PAD_X;
        padY = AbstractCard.IMG_HEIGHT * 0.75f + Settings.CARD_VIEW_PAD_Y;

        // 初始化滚动条
        this.scrollBar = new ScrollBar(this);
        this.scrollBar.move(0.0f, -30.0f * Settings.scale);
    }

    private void open(NobleCardGroup nobleCards) {
        // 保存传入的卡组
        this.nobleCards = nobleCards;

        AbstractDungeon.player.releaseCard();
        CardCrawlGame.sound.play("DECK_OPEN");
        
        // 重置滚动位置
        this.currentDiffY = this.scrollLowerBound;
        this.grabStartY = this.scrollLowerBound;
        this.grabbedScreen = false;
        
        // 初始化卡牌位置
        this.hideCards();
        
        // 设置屏幕状态
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = curScreen();

        // 隐藏战斗面板和显示黑屏
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);

        // 计算滚动边界
        this.calculateScrollBounds();
    }

    @Override
    public void reopen() {
        AbstractDungeon.screen = curScreen();
        AbstractDungeon.isScreenUp = true;
    }

    @Override
    public void openingSettings() {
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.settingsScreen.open();
    }

    @Override
    public void openingDeck() {
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.deckViewScreen.open();
    }

    @Override
    public void openingMap() {
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.dungeonMapScreen.open(false);
    }

    @Override
    public void scrolledUsingBar(float newPercent) {
        this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        this.updateBarPosition();
    }

    @Override
    public void close() {
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NONE;
        AbstractDungeon.isScreenUp = false;
        if (AbstractDungeon.getCurrRoom() != null && 
        AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            AbstractDungeon.overlayMenu.showCombatPanels();
        }
        AbstractDungeon.overlayMenu.hideBlackScreen();
		
        AbstractDungeon.overlayMenu.cancelButton.hide();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (this.shouldShowScrollBar()) {
            this.scrollBar.render(sb);
        }
        
        if (this.nobleCards != null) {
            if (this.hoveredCard == null) {
                this.nobleCards.render(sb);
            } else {
                // 渲染除悬停卡牌外的所有卡牌
                for (AbstractCard card : this.nobleCards.group) {
                    if (card != this.hoveredCard) {
                        card.render(sb);
                    }
                }
                
                // 渲染悬停卡牌的阴影和卡牌本身
                this.hoveredCard.renderHoverShadow(sb);
                this.hoveredCard.render(sb);
                FontHelper.renderDeckViewTip(sb, HEADER_INFO, 96.0f * Settings.scale, Settings.CREAM_COLOR);
            }
        }
    }

    @Override
    public void update() {
        boolean isDraggingScrollBar = false;
        if (this.shouldShowScrollBar()) {
            isDraggingScrollBar = this.scrollBar.update();
        }
        
        if (!isDraggingScrollBar) {
            this.updateScrolling();
        }
        
        this.updatePositions();
        this.updateClicking();
    }

    @Override
    public boolean allowOpenDeck() {
        return true;
    }

    @Override
    public boolean allowOpenMap() {
        return true;
    }

    // 辅助方法
    private void calculateScrollBounds() {
        if (this.nobleCards != null && this.nobleCards.size() > 10) {
            int scrollTmp = this.nobleCards.size() / CARDS_PER_LINE - 2;
            if (this.nobleCards.size() % CARDS_PER_LINE != 0) {
                ++scrollTmp;
            }
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + (float)scrollTmp * padY;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
    }

    private void hideCards() {
        if (this.nobleCards == null) return;
        
        int lineNum = 0;
        for (int i = 0; i < this.nobleCards.size(); ++i) {
            int mod = i % CARDS_PER_LINE;
            if (mod == 0 && i != 0) {
                ++lineNum;
            }
            AbstractCard card = this.nobleCards.group.get(i);
            card.current_x = drawStartX + (float)mod * padX;
            card.current_y = drawStartY + this.currentDiffY - (float)lineNum * padY - MathUtils.random(100.0f * Settings.scale, 200.0f * Settings.scale);
            card.targetDrawScale = 0.75f;
            card.drawScale = 0.75f;
            card.setAngle(0.0f, true);
        }
    }

    private void updateScrolling() {
        int y = InputHelper.mY;
        if (!this.grabbedScreen) {
            if (InputHelper.scrolledDown) {
                this.currentDiffY += Settings.SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.currentDiffY -= Settings.SCROLL_SPEED;
            }
            if (InputHelper.justClickedLeft) {
                this.grabbedScreen = true;
                this.grabStartY = (float)y - this.currentDiffY;
            }
        } else if (InputHelper.isMouseDown) {
            this.currentDiffY = (float)y - this.grabStartY;
        } else {
            this.grabbedScreen = false;
        }
        
        this.resetScrolling();
        this.updateBarPosition();
    }

    private void updatePositions() {
        this.hoveredCard = null;
        if (this.nobleCards == null) return;
        
        int lineNum = 0;
        for (int i = 0; i < this.nobleCards.size(); ++i) {
            int mod = i % CARDS_PER_LINE;
            if (mod == 0 && i != 0) {
                ++lineNum;
            }
            
            AbstractCard card = this.nobleCards.group.get(i);
            card.target_x = drawStartX + (float)mod * padX;
            card.target_y = drawStartY + this.currentDiffY - (float)lineNum * padY;
            card.update();
            card.updateHoverLogic();
            
            if (card.hb.hovered) {
                this.hoveredCard = card;
            }
        }
    }

    private void updateClicking() {
        if (this.hoveredCard != null) {
            if (InputHelper.justClickedLeft) {
                this.clickStartedCard = this.hoveredCard;
            }
            
            if ((InputHelper.justReleasedClickLeft && this.hoveredCard == this.clickStartedCard)) {
                InputHelper.justReleasedClickLeft = false;
                // 处理卡牌点击，例如打开卡牌详情弹窗
                CardCrawlGame.cardPopup.open(this.hoveredCard, this.nobleCards);
                this.clickStartedCard = null;
            }
        } else {
            this.clickStartedCard = null;
        }
    }

    private void resetScrolling() {
        if (this.currentDiffY < this.scrollLowerBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
        } else if (this.currentDiffY > this.scrollUpperBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
        }
    }

    private boolean shouldShowScrollBar() {
        return this.scrollUpperBound > SCROLL_BAR_THRESHOLD;
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
        this.scrollBar.parentScrolledToPercent(percent);
    }
}