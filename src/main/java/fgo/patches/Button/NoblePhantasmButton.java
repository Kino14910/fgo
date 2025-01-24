package fgo.patches.Button;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import fgo.action.FgoNpAction;
import fgo.action.NoblePhantasmSelectAction;
import fgo.characters.Master;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fgo.powers.SealNPPower;
import fgo.util.GeneralUtils;

import static fgo.util.GeneralUtils.addToBot;

public class NoblePhantasmButton {
    public static NoblePhantasmButton inst;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("fgo:NoblePhantasmButton");
    private final Vector2 position = new Vector2();
    public Hitbox hb;
    private final Color renderColor;
    private static final Texture NP_MAX = ImageMaster.loadImage("fgo/images/UI_Master/np max.png");
    public NoblePhantasmButton(float x, float y) {
        this.hb = new Hitbox(64.0F * Settings.scale, 64.0F * Settings.scale);
        this.position.x = x;
        this.position.y = y;
        this.hb.move(x, y);
        this.renderColor = Color.WHITE.cpy();
    }

    public void update() {
        this.hb.update();
        //this.position.x = AbstractDungeon.player.hb.x - 20.0F * Settings.scale;
        this.hb.move(this.position.x, this.position.y);
        if (this.hb.hovered) {
            if (InputHelper.justClickedLeft && Master.fgoNp >= 100 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
                InputHelper.justClickedLeft = false;
                addToBot(new FgoNpAction(-300));

                boolean isUpgraded = Master.fgoNp >= 200;
                int amount = Master.fgoNp == 300 ? 2 : 1;

                addToBot(new NoblePhantasmSelectAction(isUpgraded, amount));

                CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
            }
            if (this.hb.justHovered) {
                CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
            }
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && Master.fgoNp >= 100) {
                TipHelper.renderGenericTip(0, this.hb.cY + 64.0F * Settings.scale, orbString.NAME, orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2]);
            }
            this.renderColor.r = 1.0F;
            this.renderColor.g = 1.0F;
            this.renderColor.b = 1.0F;
        } else {
            this.renderColor.r = 0.8F;
            this.renderColor.g = 0.8F;
            this.renderColor.b = 0.8F;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.renderColor);
        if (Master.fgoNp >= 100 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
            sb.draw(NP_MAX, this.hb.cX - 32.0F, this.hb.cY - 32.0F, 64.0F, 64.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
            //FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "√", this.hb.cX, this.hb.cY, this.renderColor);
        }
        this.hb.render(sb);
        if (this.hb.hovered) {
            CardCrawlGame.cursor.render(sb);
        }
    }


}