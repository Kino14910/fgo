package fgo.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fgo.cards.DreamUponTheStars;
import fgo.patches.Enum.FGOCardColor;
import fgo.patches.Enum.ThmodClassEnum;
import fgo.patches.MainMenuUIFgoPatch;
import fgo.patches.PictureSelectFgoPatch;
import fgo.relics.Avenger;

import java.util.ArrayList;
import java.util.Collections;

public class master extends CustomPlayer{
    private static final String[] ORB_TEXTURES = new String[] {
            "fgo/images/UI_Master/EPanel/layer5.png",
            "fgo/images/UI_Master/EPanel/layer4.png",
            "fgo/images/UI_Master/EPanel/layer3.png",
            "fgo/images/UI_Master/EPanel/layer2.png",
            "fgo/images/UI_Master/EPanel/layer1.png",
            "fgo/images/UI_Master/EPanel/layer0.png",
            "fgo/images/UI_Master/EPanel/layer5d.png",
            "fgo/images/UI_Master/EPanel/layer4d.png",
            "fgo/images/UI_Master/EPanel/layer3d.png",
            "fgo/images/UI_Master/EPanel/layer2d.png",
            "fgo/images/UI_Master/EPanel/layer1d.png"};
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public static float FgoNp_BAR_HEIGHT = 20.0F * Settings.scale;
    private Hitbox FgoNp;
    public Color FgoNpBarColor1 = CardHelper.getColor(204, 128, 28);
    public Color FgoNpBarColor2 = CardHelper.getColor(238, 175, 10);
    public Color FgoNpBarColor3 = CardHelper.getColor(242, 236, 103);
    public Color FgoNpShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color FgoNpBgColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color FgoNptextColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString("SpireHeartText").TEXT;
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("NPText").TEXT;
    private float FgoNpWidth;
    public static int fgoNp;
    public master(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.MASTER_CLASS, ORB_TEXTURES, "fgo/images/UI_Master/energyBlueVFX.png", LAYER_SPEED, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        initializeClass(
                String.valueOf(MainMenuUIFgoPatch.refreshSkinFgo()),
                "fgo/images/char_Master/shoulder2.png", "fgo/images/char_Master/shoulder1.png",
                "fgo/images/char_Master/fallen.png",
                getLoadout(),
                0.0F, 5.0F,
                240.0F, 300.0F,
                new EnergyManager(3)
        );

        fgoNp = 0;
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.addAll(Collections.nCopies(4, "Strike"));
        retVal.addAll(Collections.nCopies(4, "Defend"));
        Collections.addAll(retVal,
                "DreamUponTheStars",
                "CharismaOfHope"
//                "EternalMemories",
//                "BeautifulJourney",
//                "Failnaught"
        );
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("SuitcaseFgo");
        UnlockTracker.markRelicAsSeen("SuitcaseFgo");
        retVal.add("HalloweenRoyalty");
        UnlockTracker.markRelicAsSeen("HalloweenRoyalty");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选人物界面的文字描述
        return new CharSelectInfo(
                TEXT[1],
                TEXT[2],
                72,
                72,
                0,
                99,
                5,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {return TEXT[3];}

    @Override
    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return FGOCardColor.FGO;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new DreamUponTheStars();
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 6;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg = PictureSelectFgoPatch.updateBgImg();

        CardCrawlGame.sound.playV("MASTER_CHOOSE", 0.8F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {return TEXT[4];}

    @Override
    public AbstractPlayer newInstance() {
        return new master(this.name);
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
    }

    @Override
    public String getVampireText() {return Vampires.DESCRIPTIONS[1];}

    @Override
    public String getSpireHeartText() {
        return TEXT[0];
    }

    @Override
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }

    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("fgo/images/char_Master/Victory1.png"));
        panels.add(new CutscenePanel("fgo/images/char_Master/Victory2.png"));
        panels.add(new CutscenePanel("fgo/images/char_Master/Victory3.png"));
        return panels;
    }

    public void TruthValueUpdatedEvent() {
        if (fgoNp > 200) {
            this.FgoNpWidth = this.hb.width * (fgoNp - 200) / 100.0F;
        } else if (fgoNp > 100) {
            this.FgoNpWidth = this.hb.width * (fgoNp - 100) / 100.0F;
        } else {
            this.FgoNpWidth = this.hb.width * fgoNp / 100.0F;
        }
    }

    public void renderHealth(SpriteBatch sb) {
        super.renderHealth(sb);
        float x = this.hb.cX - this.hb.width / 2.0F;
        float y = this.hb.cY + this.hb.height / 2.0F;
        this.FgoNp = new Hitbox(x, y, this.hb_w, FgoNp_BAR_HEIGHT);
        this.FgoNp.render(sb);
        TruthValueBgRender(sb, x, y);
        renderTruthValueBar(sb, x);
        TruthValueText(sb);

        this.FgoNp.update();
        if (this.FgoNp.hovered) {
            if (AbstractDungeon.player.hasRelic(Avenger.ID)) {
                TipHelper.renderGenericTip(0, y, NPTEXT[0], NPTEXT[4]);
            } else {
                TipHelper.renderGenericTip(0, y, NPTEXT[0], NPTEXT[1]);
            }
        }
    }

    private void renderTruthValueBar(SpriteBatch sb, float x) {
        if (fgoNp > 200) {
            sb.setColor(this.FgoNpBarColor3);
        } else if (fgoNp > 100) {
            sb.setColor(this.FgoNpBarColor2);
        } else {
            sb.setColor(this.FgoNpBarColor1);
        }
        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, this.FgoNpWidth, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.FgoNpWidth, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }

    private void TruthValueText(SpriteBatch sb) {
        float tmp = this.FgoNptextColor.a;
        FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, master.fgoNp + "%", this.hb.cX, this.FgoNp.cY, this.FgoNptextColor);
        this.FgoNptextColor.a = tmp;
    }

    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        if (fgoNp > 200) {
            sb.setColor(this.FgoNpBarColor2);
        } else if (fgoNp > 100) {
            sb.setColor(this.FgoNpBarColor1);
        } else {
            sb.setColor(this.FgoNpShadowColor);
        }
        //宝具值外框颜色。
        sb.draw(ImageMaster.HB_SHADOW_L, x - FgoNp_BAR_HEIGHT, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_B, x, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, this.hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width, this.FgoNp.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.setColor(this.FgoNpBgColor);
        //宝具值内框灰色。
        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, this.hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }
}
