package fgo.patches;

import basemod.ReflectionHacks;
import basemod.TopPanelItem;
import fgo.cards.colorless.mash.BlackBarrel;
import fgo.cards.colorless.Revelation;
import fgo.cards.colorless.mash.WallOfSnowflakes;
import fgo.cards.tempCards.FlamesofApplause;
import fgo.cards.fgo.*;
import fgo.cards.noblecards.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.screens.MasterDeckViewScreen;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import fgo.patches.Enum.ThmodClassEnum;
import fgo.powers.UnlimitedPower;
import fgo.relics.ArchetypeORT;
import fgo.relics.BowInsignia;
import fgo.relics.MechanicalProtector;
import fgo.relics.PoisonApple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static fgo.FGOMod.makeID;

public class CurrentNobleCardsTopPanelItem extends TopPanelItem {
    public static final String ID = makeID(CurrentNobleCardsTopPanelItem.class.getSimpleName());
    private static final float tipYpos = Settings.HEIGHT - 120.0F * Settings.scale;
    private static final Texture IMAGE = ImageMaster.loadImage("fgo/images/UI_Master/NobleTopPanel.png");
    private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    private static final String[] TEXT = STRINGS.TEXT;
    private static final Set<AbstractDungeon.CurrentScreen> validScreens = new HashSet<>();
    private static final CardGroup poolGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    public static boolean open = false;

    static {
        validScreens.add(AbstractDungeon.CurrentScreen.COMBAT_REWARD);
        validScreens.add(AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW);
        validScreens.add(AbstractDungeon.CurrentScreen.DEATH);
        validScreens.add(AbstractDungeon.CurrentScreen.BOSS_REWARD);
        validScreens.add(AbstractDungeon.CurrentScreen.SHOP);
        validScreens.add(AbstractDungeon.CurrentScreen.MAP);
    }

    public float flashTimer;

    public CurrentNobleCardsTopPanelItem() {
        super(IMAGE, ID);
    }

    public boolean isClickable() {
        if (!AbstractDungeon.player.chosenClass.equals(ThmodClassEnum.MASTER_CLASS)) {
            return false;
        }
        return (!AbstractDungeon.isScreenUp || (validScreens.contains(AbstractDungeon.screen) && AbstractDungeon.previousScreen != AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.previousScreen != AbstractDungeon.CurrentScreen.BOSS_REWARD) || open);
    }

    public void update() {
        updateFlash();
        super.update();
    }

    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player.chosenClass.equals(ThmodClassEnum.MASTER_CLASS)) {
            boolean ic = isClickable();
            render(sb, ic ? Color.WHITE : Color.DARK_GRAY);
            renderFlash(sb);
            renderHover();
        }
    }

    protected void onClick() {
        if (isClickable()) {
            if (open && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                AbstractDungeon.closeCurrentScreen();
                CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
                open = false;
            } else {
                if (!AbstractDungeon.isScreenUp) {
                    open();
                    return;
                }
                switch (AbstractDungeon.screen) {
                    case COMBAT_REWARD:
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                        open();
                        break;
                    case MASTER_DECK_VIEW:
                        AbstractDungeon.closeCurrentScreen();
                        open();
                        break;
                    case DEATH:
                        AbstractDungeon.deathScreen.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                        open();
                        break;
                    case BOSS_REWARD:
                        AbstractDungeon.bossRelicScreen.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                        open();
                        break;
                    case SHOP:
                        AbstractDungeon.overlayMenu.cancelButton.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                        open();
                        break;
                    case MAP:
                        if (AbstractDungeon.dungeonMapScreen.dismissable) {
                            if (AbstractDungeon.previousScreen != null)
                                AbstractDungeon.screenSwap = true;
                            AbstractDungeon.closeCurrentScreen();
                        } else {
                            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                        }
                        open();
                        break;
                }
            }
        }
    }

    private void open() {
        poolGroup.clear();
        poolGroup.addToTop(new EternalMemories());
        poolGroup.addToTop(new BeautifulJourney());
        poolGroup.addToTop(new Failnaught());
        poolGroup.addToTop(new TsumukariMuramasa());
        poolGroup.addToTop(new ImitationGodForce());
        poolGroup.addToTop(new BridalSpinWheel());
        poolGroup.addToTop(new IraLupus());
        poolGroup.addToTop(new LostLonginus());
        poolGroup.addToTop(new WickerMan());
        poolGroup.addToTop(new ExcaliburGalatine());
        poolGroup.addToTop(new Overload());
        poolGroup.addToTop(new GardenOfAvalon());

        if (AbstractDungeon.actNum >= 4) {
            poolGroup.addToTop(new ElementaryMyDear());
            poolGroup.addToTop(new GreatRamNautilus());
        }

        if (AbstractDungeon.actNum >= 3) {
            poolGroup.addToTop(new LieLikeVortigern());
            poolGroup.addToTop(new RoadlessCamelot());
            poolGroup.addToTop(new BlackdogGalatine());
            poolGroup.addToTop(new FetchFailnaught());
            poolGroup.addToTop(new InnocenceAroundight());
            //poolGroup.addToTop(new HollowHeartAlbion());
            poolGroup.addToTop(new Sevendrive());
        }

        //如果你有龙之炉心。
        if (CardHelper.hasCardWithID(DragonCore.ID)) {
            poolGroup.addToTop(new Excalibur());
        }
        //如果你有雪花之壁或悖论构造体。
        if (CardHelper.hasCardWithID(WallOfSnowflakes.ID) || CardHelper.hasCardWithID(BlackBarrel.ID)) {
            poolGroup.addToTop(new Camelot());
        }
        //如果你没有羽翼之靴。
        if (!AbstractDungeon.player.hasRelic(WingBoots.ID) && AbstractDungeon.actNum >= 2) {
            poolGroup.addToTop(new EnferChateaudIf());
        }
        //如果你有虚数美术、黄房子或澪标之魂。
        if (CardHelper.hasCardWithID(VoidSpaceFineArts.ID) || CardHelper.hasCardWithID(TheYellowHouse.ID) || CardHelper.hasCardWithID(Insanity.ID)) {
            poolGroup.addToTop(new Desterrennacht());
        }
        //如果你有灿然圣光之复权。
        if (CardHelper.hasCardWithID(Revelation.ID)) {
            poolGroup.addToTop(new LaPucelle());
        }
        //如果你有机关护具。
        if (AbstractDungeon.player.hasRelic(MechanicalProtector.ID)) {
            poolGroup.addToTop(new Shishifunjin());
        }
        //如果你有草莓、梨子、芒果、李家华夫饼或冰淇淋。
        if (AbstractDungeon.player.hasRelic(Strawberry.ID) || AbstractDungeon.player.hasRelic(Waffle.ID) || AbstractDungeon.player.hasRelic(Mango.ID) || AbstractDungeon.player.hasRelic(Pear.ID) || AbstractDungeon.player.hasRelic(IceCream.ID)) {
            poolGroup.addToTop(new Fragarach());
        }
        //如果你有Archetype ORT。
        if (AbstractDungeon.player.hasRelic(ArchetypeORT.ID)) {
            poolGroup.addToTop(new LastSunXibalba());
        }
        //如果你有灵体外质、天鹅绒颈圈或添水。
        if (AbstractDungeon.player.hasRelic(Ectoplasm.ID) || AbstractDungeon.player.hasRelic(VelvetChoker.ID) || AbstractDungeon.player.hasRelic(Sozu.ID)) {
            poolGroup.addToTop(new MahaPralaya());
        }
        //如果你有冥界佑护。
        if (CardHelper.hasCardWithID(BlessingOfKur.ID)) {
            poolGroup.addToTop(new KurKigalIrkalla());
        }
        //如果你有喝彩之焰。
        if (CardHelper.hasCardWithID(FlamesofApplause.ID)) {
            poolGroup.addToTop(new ParadisChateaudIf());
        }
        //如果你有疯狂的晚宴。
        if (CardHelper.hasCardWithID(CrownedWithLife.ID)) {
            poolGroup.addToTop(new SecondLife());
        }
        //如果你有毒苹果。
        if (AbstractDungeon.player.hasRelic(PoisonApple.ID)) {
            poolGroup.addToTop(new YewBow());
        }
        //如果你有弓之印章，并且还没进入无限剑制。
        if (!AbstractDungeon.player.hasPower(UnlimitedPower.POWER_ID) && AbstractDungeon.player.hasRelic(BowInsignia.ID)) {
            poolGroup.addToTop(new Unlimited());
        }

        open = true;
        CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
        AbstractDungeon.deckViewScreen.open();
    }

    public void flash() {
        this.flashTimer = 2.0F;
    }

    private void updateFlash() {
        if (this.flashTimer != 0.0F) {
            this.flashTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    public void renderHover() {
        if ((getHitbox()).hovered) {
            TipHelper.renderGenericTip((getHitbox()).x, tipYpos, TEXT[0], TEXT[1]);
        }
    }

    public void renderFlash(SpriteBatch sb) {
        float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, this.flashTimer / 2.0F);
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.flashTimer * 2.0F));

        float halfWidth = this.image.getWidth() / 2.0F;
        float halfHeight = this.image.getHeight() / 2.0F;
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp, Settings.scale + tmp, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp * 0.66F, Settings.scale + tmp * 0.66F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp / 3.0F, Settings.scale + tmp / 3.0F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);

        sb.setBlendFunction(770, 771);
    }

    @SpirePatch(clz = TopPanel.class, method = "updateDeckViewButtonLogic")
    public static class DefinitelyNotViewingPool {
        @SpireInsertPatch(locator = Locator.class, localvars = {"clickedDeckButton"})
        public static void viewingDeck(TopPanel __instance, boolean clickedDeckButton) {
            if (clickedDeckButton) {
                if (CurrentNobleCardsTopPanelItem.open && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                    AbstractDungeon.closeCurrentScreen();
                }
                CurrentNobleCardsTopPanelItem.open = false;
            }
        }

        private static class Locator
                extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(InputAction.class, "isJustPressed");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }


    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updateControllerInput")
    public static class ControllerUseAlt {
        @SpireInsertPatch(locator = Locator.class, localvars = {"deck"})
        public static void viewAlt(MasterDeckViewScreen __instance, @ByRef(type = "com.megacrit.cardcrawl.cards.CardGroup") Object[] deck) {
            if (CurrentNobleCardsTopPanelItem.open) {
                deck[0] = CurrentNobleCardsTopPanelItem.poolGroup;
            }
        }

        private static class Locator
                extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
                return LineFinder.findInOrder(ctMethodToPatch, fieldAccessMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updatePositions")
    public static class UpdateAltPositions {
        @SpireInsertPatch(locator = Locator.class, localvars = {"cards"})
        public static void updateAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards) {
            if (CurrentNobleCardsTopPanelItem.open) {
                cards[0] = CurrentNobleCardsTopPanelItem.poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updateClicking")
    public static class UpdateAltClick {
        @SpireInsertPatch(locator = Locator.class, localvars = {"hoveredCard", "clickStartedCard"})
        public static SpireReturn<?> openAlt(MasterDeckViewScreen __instance, AbstractCard hovered, @ByRef(type = "com.megacrit.cardcrawl.cards.AbstractCard") Object[] clickStartedCard) {
            if (CurrentNobleCardsTopPanelItem.open) {
                CardCrawlGame.cardPopup.open(hovered, CurrentNobleCardsTopPanelItem.poolGroup);
                clickStartedCard[0] = null;
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(SingleCardViewPopup.class, "open");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "calculateScrollBounds")
    public static class CalcAltBounds {
        @SpirePrefixPatch
        public static SpireReturn<?> calcAlt(MasterDeckViewScreen __instance) {
            if (CurrentNobleCardsTopPanelItem.open) {
                if (CurrentNobleCardsTopPanelItem.poolGroup.size() > 10) {
                    int scrollTmp = CurrentNobleCardsTopPanelItem.poolGroup.size() / 5 - 2;
                    if (CurrentNobleCardsTopPanelItem.poolGroup.size() % 5 != 0) {
                        scrollTmp++;
                    }
                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * (AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y));
                } else {
                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT);
                }
                ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "prevDeckSize", CurrentNobleCardsTopPanelItem.poolGroup.size());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "hideCards")
    public static class HideAltCards {
        @SpireInsertPatch(locator = Locator.class, localvars = {"cards"})
        public static void hideAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards) {
            if (CurrentNobleCardsTopPanelItem.open) {
                cards[0] = CurrentNobleCardsTopPanelItem.poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "render")
    public static class RenderAlt {
        @SpirePrefixPatch
        public static SpireReturn<?> rendering(MasterDeckViewScreen __instance, SpriteBatch sb) {
            if (CurrentNobleCardsTopPanelItem.open) {
                AbstractCard hoveredCard = ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "hoveredCard");
                if (hoveredCard == null) {
                    CurrentNobleCardsTopPanelItem.poolGroup.renderMasterDeck(sb);
                } else {
                    CurrentNobleCardsTopPanelItem.poolGroup.renderMasterDeckExceptOneCard(sb, hoveredCard);
                    hoveredCard.renderHoverShadow(sb);
                    hoveredCard.render(sb);
                }

                CurrentNobleCardsTopPanelItem.poolGroup.renderTip(sb);
                FontHelper.renderDeckViewTip(sb, CurrentNobleCardsTopPanelItem.TEXT[2], 96.0F * Settings.scale, Settings.CREAM_COLOR);
                if ((Float) ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound") > 500.0F * Settings.scale) {
                    ((ScrollBar) ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollBar")).render(sb);
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
