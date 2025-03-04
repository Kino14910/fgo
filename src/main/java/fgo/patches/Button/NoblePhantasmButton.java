package fgo.patches.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import fgo.action.FgoNpAction;
import fgo.action.NoblePhantasmSelectAction;
import fgo.characters.Master;
import fgo.powers.SealNPPower;

import static fgo.util.GeneralUtils.addToBot;

public class NoblePhantasmButton extends AbstractPanel {
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("fgo:NoblePhantasm");
    public static final String[] MSG = NoblePhantasmButton.tutorialStrings.TEXT;
    public static final String[] LABEL = NoblePhantasmButton.tutorialStrings.LABEL;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fgo:NoblePhantasm");
    public static final String[] TEXT = NoblePhantasmButton.uiStrings.TEXT;
    private final Vector2 position = new Vector2();
    private float scale = 1.0f;

//    private Hitbox hb = new Hitbox(256.0f * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale);
    private Hitbox hb = new Hitbox(AbstractDungeon.player.hb.x - 64.0F * Settings.scale, AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 12.0F * Settings.scale, 64.0F * Settings.scale, 64.0F * Settings.scale);
    private Color renderColor = Color.WHITE.cpy();
    private Color glowColor = Color.WHITE.cpy();
    private float glowAlpha = 0.0f;
    private BobEffect bob = new BobEffect(1.0f);
    private static Texture CommandSpell = ImageMaster.loadImage("fgo/images/ui/np max.png");

    public NoblePhantasmButton() {
        super(AbstractDungeon.player.hb.x - 64.0F * Settings.scale, AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 12.0F * Settings.scale, -Settings.WIDTH, AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 12.0F * Settings.scale, 8.0f * Settings.xScale, 0.0f, null, true);

    }

    @Override
    public void updatePositions() {
        float tmp;
        super.updatePositions();
        bob.update();
        if (!isHidden) {
            hb.update();
            updatePop();
        }
        if (hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.overlayMenu.combatPanelsShown)) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            if (InputHelper.justClickedLeft) {
                hb.clickStarted = true;
            }
        }
        glowAlpha += Gdx.graphics.getDeltaTime() * 3.0f;
        if (glowAlpha < 0.0f) {
            glowAlpha *= -1.0f;
        }
        glowColor.a = (tmp = MathUtils.cos(glowAlpha)) < 0.0f ? -tmp / 2.0f : tmp / 2.0f;
        if (hb.clicked && AbstractDungeon.overlayMenu.combatPanelsShown && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead() && !AbstractDungeon.player.isDead) {
            hb.clicked = false;
            hb.hovered = false;
            AbstractDungeon.dynamicBanner.hide();
            if (AbstractDungeon.player.hoveredCard != null) {
                AbstractDungeon.player.releaseCard();
            }
            if (AbstractDungeon.isScreenUp) {
                if (AbstractDungeon.previousScreen == null) {
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }
            } else {
                AbstractDungeon.previousScreen = null;
            }
            chooseNobleCard();
        }

    }
    public void chooseNobleCard() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Master.fgoNp >= 100 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
            addToBot(new FgoNpAction(-300));
            boolean isUpgraded = Master.fgoNp >= 200;
            int amount = Master.fgoNp == 300 ? 2 : 1;
            addToBot(new NoblePhantasmSelectAction(isUpgraded, amount));

            CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
        } else if(AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)){
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, TEXT[0], true));
        }
//        if (hb.justHovered) {
//            CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
//        }
//        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
//            TipHelper.renderGenericTip(1400.0f * Settings.xScale, Settings.HEIGHT - 256.0f * Settings.scale, orbString.NAME, orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2]);
//        }
        hb.hovered = false;
        InputHelper.justClickedLeft = false;
    }


    private void updatePop() {
        scale = MathHelper.scaleLerpSnap(scale, Settings.scale);
    }

    public void pop() {
        scale = 1.75f * Settings.scale;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (Master.fgoNp < 100) {
            return;
        }

        if (hb.hovered) {
            scale = 1.2f * Settings.scale;
        }
        sb.setColor(renderColor);
        sb.draw(CommandSpell, current_x, current_y, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);

        if (hb.hovered) {
            CardCrawlGame.cursor.render(sb);
        }

        if (!isHidden) {
            hb.render(sb);
        }
        if (!isHidden && hb != null && hb.hovered && !AbstractDungeon.isScreenUp && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead()) {
            TipHelper.renderGenericTip(AbstractDungeon.player.hb.x - 128.0F * Settings.scale, AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 32.0F * Settings.scale, LABEL[0], MSG[0] + MSG[1] + MSG[2]);
        } else {
            hb.hovered = false;
        }
    }

}
