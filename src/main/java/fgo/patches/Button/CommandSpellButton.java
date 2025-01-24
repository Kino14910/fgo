package fgo.patches.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.DiscardGlowEffect;
import fgo.action.FgoNpAction;
import fgo.action.NoblePhantasmSelectAction;
import fgo.cards.optionCards.ReleaseNoblePhantasm;
import fgo.cards.optionCards.RepairSpiritOrigin;
import fgo.characters.Master;
import fgo.powers.SealNPPower;

import java.util.ArrayList;
import java.util.Iterator;

import static fgo.util.GeneralUtils.addToBot;

public class CommandSpellButton {
    public static CommandSpellButton inst;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("fgo:NoblePhantasmButton");
    private final Vector2 position = new Vector2();
    public Hitbox hb;
    private final Color renderColor;
    String basePath = "fgo/images/UI_Master/CommandSpell/";
    String[] CommandSpells = {
        basePath + "CommandSpell0.png",
        basePath + "CommandSpell1.png",
        basePath + "CommandSpell2.png",
        basePath + "CommandSpell3.png"
    };
private static Texture CommandSpell = ImageMaster.loadImage("fgo/images/UI_Master/CommandSpell/CommandSpell3.png");

    public CommandSpellButton(float x, float y) {
        this.hb = new Hitbox(128.0F * Settings.scale, 128.0F * Settings.scale);
        this.position.x = x;
        this.position.y = y;
        this.hb.move(x, y);
        this.renderColor = Color.WHITE.cpy();
    }

    public void update() {
        this.hb.update();
        this.hb.move(this.position.x, this.position.y);
        if (this.hb.hovered) {
            if (InputHelper.justClickedLeft && Master.commandSpellCount > 0) {
                InputHelper.justClickedLeft = false;
                ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
                stanceChoices.add(new RepairSpiritOrigin());
                stanceChoices.add(new ReleaseNoblePhantasm());
                addToBot(new ChooseOneAction(stanceChoices));
                Master.commandSpellCount--;
                CommandSpell = ImageMaster.loadImage(CommandSpells[Master.commandSpellCount]);
                CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
            }
            if (this.hb.justHovered) {
                CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
            }
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                TipHelper.renderGenericTip(1400.0f * Settings.xScale, Settings.HEIGHT - 256.0f * Settings.scale, orbString.NAME, orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2]);
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
//        if (Master.fgoNp >= 100 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
            sb.draw(CommandSpell, this.hb.cX - 64.0F, this.hb.cY - 64.0f, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
            //FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "√", this.hb.cX, this.hb.cY, this.renderColor);
//        }
        this.hb.render(sb);
        if (this.hb.hovered) {
            CardCrawlGame.cursor.render(sb);
        }
    }

}
