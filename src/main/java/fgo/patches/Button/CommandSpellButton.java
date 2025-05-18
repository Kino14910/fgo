package fgo.patches.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import fgo.cards.optionCards.ReleaseNoblePhantasm;
import fgo.cards.optionCards.RepairSpiritOrigin;
import fgo.panel.CommandSpellPanel;

import java.util.ArrayList;

import static fgo.util.GeneralUtils.addToBot;

public class CommandSpellButton extends AbstractPanel {
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("fgo:CommandSpell");
    public static final String[] MSG = CommandSpellButton.tutorialStrings.TEXT;
    public static final String[] LABEL = CommandSpellButton.tutorialStrings.LABEL;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fgo:CommandSpell");
    public static final String[] TEXT = CommandSpellButton.uiStrings.TEXT;
    private float scale = 1.0f;

//    private Hitbox hb = new Hitbox(256.0f * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale);
    private Hitbox hb = new Hitbox(Settings.WIDTH - 128.0f * Settings.scale, Settings.HEIGHT - 320.0F * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale);
    private Color renderColor = Color.WHITE.cpy();
    private Color glowColor = Color.WHITE.cpy();
    private float glowAlpha = 0.0f;
    private BobEffect bob = new BobEffect(1.0f);

    public CommandSpellButton() {
        super(Settings.WIDTH - 128.0f * Settings.scale, Settings.HEIGHT - 320.0F * Settings.scale, Settings.WIDTH, Settings.HEIGHT - 320.0F * Settings.scale, 8.0f * Settings.xScale, 0.0f, null, true);
    }

    @Override
    public void updatePositions() {
        float tmp;
        super.updatePositions();
        this.bob.update();
        if (!this.isHidden) {
            this.hb.update();
            this.updatePop();
        }
        if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.overlayMenu.combatPanelsShown)) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            if (InputHelper.justClickedLeft) {
                this.hb.clickStarted = true;
            }
        }
        this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0f;
        if (this.glowAlpha < 0.0f) {
            this.glowAlpha *= -1.0f;
        }
        this.glowColor.a = (tmp = MathUtils.cos(this.glowAlpha)) < 0.0f ? -tmp / 2.0f : tmp / 2.0f;
        if (this.hb.clicked && AbstractDungeon.overlayMenu.combatPanelsShown && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead() && !AbstractDungeon.player.isDead) {
            this.hb.clicked = false;
            this.hb.hovered = false;
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
            this.chooseCommandSpell();
        }
    }

    public void chooseCommandSpell() {
        AbstractPlayer p = AbstractDungeon.player;
        if (CommandSpellPanel.commandSpellCount > 0) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new RepairSpiritOrigin());
            stanceChoices.add(new ReleaseNoblePhantasm());
            addToBot(new ChooseOneAction(stanceChoices));
            CommandSpellPanel.commandSpellCount--;
            CommandSpellPanel.CommandSpell = ImageMaster.loadImage("fgo/images/ui/CommandSpell/CommandSpell" + CommandSpellPanel.commandSpellCount + ".png");
//            CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, TEXT[0], true));
        }
//        if (this.hb.justHovered) {
//            CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
//        }
//        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
//            TipHelper.renderGenericTip(1400.0f * Settings.xScale, Settings.HEIGHT - 256.0f * Settings.scale, orbString.NAME, orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2]);
//        }
        this.hb.hovered = false;
        InputHelper.justClickedLeft = false;
    }


    private void updatePop() {
        this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
    }

    public void pop() {
        this.scale = 1.75f * Settings.scale;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (this.hb.hovered) {
            this.scale = 1.2f * Settings.scale;
        }
        sb.setColor(this.renderColor);
        CommandSpellPanel.CommandSpell = ImageMaster.loadImage("fgo/images/ui/CommandSpell/CommandSpell" + CommandSpellPanel.commandSpellCount + ".png");
        sb.draw(CommandSpellPanel.CommandSpell, current_x, current_y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, 0.0F, 0, 0, 128, 128, false, false);

        if (this.hb.hovered) {
            CardCrawlGame.cursor.render(sb);
        }

        if (!this.isHidden) {
            this.hb.render(sb);
        }
        if (!this.isHidden && this.hb != null && this.hb.hovered && !AbstractDungeon.isScreenUp && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead()) {
            TipHelper.renderGenericTip(1400.0f * Settings.xScale, Settings.HEIGHT - 256.0f * Settings.scale, LABEL[0], MSG[0] + MSG[1] + MSG[2]);
        } else {
            this.hb.hovered = false;
        }
    }

}
