package fgo.ui.buttons;

import static fgo.FGOMod.uiPath;
import static fgo.utils.ModHelper.addToBot;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

import fgo.action.NoblePhantasmSelectAction;
import fgo.characters.Master;
import fgo.powers.SealNPPower;

public class NoblePhantasmButton extends AbstractPanel {
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("fgo:NoblePhantasm");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fgo:NoblePhantasm");

    private final Vector2 position = new Vector2();
    private float scale = 1.0f;
    private final Hitbox hb;
    private final Color renderColor = Color.WHITE.cpy();
    private static final Texture NP_MAX = ImageMaster.loadImage(uiPath("np_max"));

    public NoblePhantasmButton() {
        super(
            AbstractDungeon.player.hb.x - 48.0F * Settings.scale,
            AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 16.0F * Settings.scale,
            -Settings.WIDTH,
            AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 16.0F * Settings.scale,
            8.0f * Settings.xScale,
            0.0f,
            null,
            true
        );
        hb = new Hitbox(
            AbstractDungeon.player.hb.x - 64.0F * Settings.scale,
            AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 12.0F * Settings.scale,
            64.0F * Settings.scale,
            64.0F * Settings.scale
        );
    }

    @Override
    public void updatePositions() {
        super.updatePositions();
        hb.update();
        scale = MathHelper.scaleLerpSnap(scale, Settings.scale);

        if (hb.hovered) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            if (InputHelper.justClickedLeft) {
                InputHelper.justClickedLeft = false;
                chooseNobleCard();
                CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
            }
        }
    }

    private void chooseNobleCard() {
        AbstractPlayer p = AbstractDungeon.player;

        // if (p.hasPower(SealNPPower.POWER_ID)) {
        //     AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, uiStrings.TEXT[0], true));
        //     return;
        // } 
        
        if (Master.fgoNp >= 100) {
            Master.fgoNp = 0;
            ((Master)p).TruthValueUpdatedEvent();
            addToBot(new NoblePhantasmSelectAction());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(renderColor);

        if (!AbstractDungeon.player.hasPower(SealNPPower.POWER_ID) && Master.fgoNp >= 100 && !AbstractDungeon.isScreenUp) {
            sb.draw(NP_MAX, current_x, current_y, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);
        }

        hb.render(sb);

        if (shouldRenderTip()) {
            TipHelper.renderGenericTip(
                AbstractDungeon.player.hb.x - 128.0F * Settings.scale,
                AbstractDungeon.player.hb.y + AbstractDungeon.player.hb.height - 32.0F * Settings.scale,
                tutorialStrings.LABEL[0],
                tutorialStrings.TEXT[0] + tutorialStrings.TEXT[1] + tutorialStrings.TEXT[2]
            );
        } 
    }

    private boolean shouldRenderTip() {
        return !isHidden &&
               hb != null &&
               hb.hovered &&
               !AbstractDungeon.isScreenUp &&
               AbstractDungeon.getMonsters() != null &&
               !AbstractDungeon.getMonsters().areMonstersDead();
    }
}
