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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fgo.cards.fgo.CharismaOfHope;
import fgo.cards.fgo.Defend;
import fgo.cards.fgo.DreamUponTheStars;
import fgo.cards.fgo.Strike;
import fgo.cards.noblecards.BeautifulJourney;
import fgo.cards.noblecards.EternalMemories;
import fgo.cards.noblecards.Failnaught;
import fgo.cards.noblecards.IraLupus;
import fgo.panel.CommandSpellPanel;
import fgo.patches.Enum.FGOCardColor;
import fgo.patches.Enum.ThmodClassEnum;
import fgo.patches.MainMenuUIFgoPatch;
import fgo.patches.PictureSelectFgoPatch;
import fgo.relics.Avenger;
import fgo.relics.SuitcaseFgo;

import java.util.ArrayList;

public class Master extends CustomPlayer{
    private static final String[] ORB_TEXTURES = new String[] {
            "fgo/images/ui/EPanel/layer5.png",
            "fgo/images/ui/EPanel/layer4.png",
            "fgo/images/ui/EPanel/layer3.png",
            "fgo/images/ui/EPanel/layer2.png",
            "fgo/images/ui/EPanel/layer1.png",
            "fgo/images/ui/EPanel/layer0.png",
            "fgo/images/ui/EPanel/layer5d.png",
            "fgo/images/ui/EPanel/layer4d.png",
            "fgo/images/ui/EPanel/layer3d.png",
            "fgo/images/ui/EPanel/layer2d.png",
            "fgo/images/ui/EPanel/layer1d.png"};
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public static float FgoNp_BAR_HEIGHT = 20.0F * Settings.scale;
    private Hitbox FgoNPhb;
    public Color FgoNpBarColor1 = CardHelper.getColor(204, 128, 28);
    public Color FgoNpBarColor2 = CardHelper.getColor(238, 175, 10);
    public Color FgoNpBarColor3 = CardHelper.getColor(242, 236, 103);
    public Color FgoNpShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color FgoNpBgColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color FgoNptextColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString("fgo:SpireHeartText").TEXT;
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fgo:NPText").TEXT;
    private float FgoNpWidth;
    public static int fgoNp;
    public Master(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.MASTER_CLASS, ORB_TEXTURES, "fgo/images/ui/energyBlueVFX.png", LAYER_SPEED, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        initializeClass(
                String.valueOf(MainMenuUIFgoPatch.refreshSkinFgo()),
                "fgo/images/character/shoulder2.png", "fgo/images/character/shoulder1.png",
                "fgo/images/character/fallen.png",
                getLoadout(),
                0.0F, 5.0F,
                240.0F, 300.0F,
                new EnergyManager(3)
        );
    }


    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(CharismaOfHope.ID);
        retVal.add(DreamUponTheStars.ID);

        //宝具卡
        retVal.add(EternalMemories.ID);
        retVal.add(BeautifulJourney.ID);
        retVal.add(IraLupus.ID);
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SuitcaseFgo.ID);
//        retVal.add("HalloweenRoyalty");
//        retVal.add(CommandSpell.ID);
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
        return new Master(this.name);
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
        int base = fgoNp > 200 ? 200 : (fgoNp > 100 ? 100 : 0);
        this.FgoNpWidth = this.hb.width * (fgoNp - base) / 100.0F;
    }

    public void renderHealth(SpriteBatch sb) {
        super.renderHealth(sb);
        float x = this.hb.cX - this.hb.width / 2.0F;
        float y = this.hb.cY + this.hb.height / 2.0F;
        this.FgoNPhb = new Hitbox(x, y, this.hb_w, FgoNp_BAR_HEIGHT);
        this.FgoNPhb.render(sb);
        TruthValueBgRender(sb, x, y);
        renderTruthValueBar(sb, x);
        TruthValueText(sb);

        this.FgoNPhb.update();
        if (this.FgoNPhb.hovered) {
            if (AbstractDungeon.player.hasRelic(Avenger.ID)) {
                TipHelper.renderGenericTip(0, y, NPTEXT[0], NPTEXT[4]);
            } else {
                TipHelper.renderGenericTip(0, y, NPTEXT[0], NPTEXT[1]);
            }
        }
    }

    private void renderTruthValueBar(SpriteBatch sb, float x) {
        Color color = fgoNp > 200 ? FgoNpBarColor3 : (fgoNp > 100 ? FgoNpBarColor2 : FgoNpBarColor1);
        sb.setColor(color);

        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, this.FgoNpWidth, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.FgoNpWidth, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }

    private void TruthValueText(SpriteBatch sb) {
        float tmp = this.FgoNptextColor.a;
        FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, Master.fgoNp + "%", this.hb.cX, this.FgoNPhb.cY, this.FgoNptextColor);
        this.FgoNptextColor.a = tmp;
    }

    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        Color color = fgoNp > 200 ? FgoNpBarColor2 : (fgoNp > 100 ? FgoNpBarColor1 : FgoNpShadowColor);
        sb.setColor(color);

        //宝具值外框颜色。
        sb.draw(ImageMaster.HB_SHADOW_L, x - FgoNp_BAR_HEIGHT, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_B, x, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, this.hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width, this.FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.setColor(this.FgoNpBgColor);
        //宝具值内框灰色。
        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, this.hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }
}
