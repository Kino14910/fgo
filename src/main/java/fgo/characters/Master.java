package fgo.characters;

import basemod.abstracts.CustomPlayer;

import com.badlogic.gdx.Gdx;
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

import fgo.cards.fgo.CharismaOfHope;
import fgo.cards.fgo.Defend;
import fgo.cards.fgo.DreamUponTheStars;
import fgo.cards.fgo.Strike;
import fgo.cards.noblecards.BeautifulJourney;
import fgo.cards.noblecards.EternalMemories;
import fgo.cards.noblecards.IraLupus;
import fgo.patches.Enum.FGOCardColor;
import fgo.patches.Enum.ThmodClassEnum;
import fgo.patches.MainMenuUIFgoPatch;
import fgo.patches.PictureSelectFgoPatch;
import fgo.relics.SuitcaseFgo;
import java.util.ArrayList;
import java.util.Collections;

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
    private float FgoNpHideTimer = 1.0f;
    public static int fgoNp;
    
    public Master(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.MASTER_CLASS, ORB_TEXTURES, "fgo/images/ui/energyBlueVFX.png", LAYER_SPEED, null, null);
        dialogX = drawX + 0.0F * Settings.scale;
        dialogY = drawY + 220.0F * Settings.scale;

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

    // public void setNP(int value) {
    //     fgoNp = MathUtils.clamp(value, 0, 300);
    // }

    // public int getNP() {
    //     return fgoNp;
    // }


    @Override
    public ArrayList<String> getStartingDeck() {
        // int charIndex = MainMenuUIFgoPatch.modifierIndexes;

        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();

        Collections.addAll(retVal,
            Strike.ID,
            Strike.ID,
            Strike.ID,
            Strike.ID,
            Defend.ID,
            Defend.ID,            
            Defend.ID,
            Defend.ID,
            CharismaOfHope.ID,
            DreamUponTheStars.ID
        );
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SuitcaseFgo.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选人物界面的文字描述
        return new CharSelectInfo(
                TEXT[1],
                TEXT[2],
                76,
                76,
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
        energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {return TEXT[4];}

    @Override
    public AbstractPlayer newInstance() {
        return new Master(name);
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

    private static boolean isNewGame = true;

    // @Override
    // public void preBattlePrep() {
    //     super.preBattlePrep();
        
    //     if (isNewGame) {
    //         // 只在首次游戏时初始化NobleDeck
    //         NobleDeck.nobleCards.clear();
    //         isNewGame = false;
    //     }
        
    //     // 每场战斗前转移宝具卡
    //     ArrayList<AbstractCard> nobleCards = new ArrayList<>();
    //     for (AbstractCard card : masterDeck.group) {
    //         if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
    //             nobleCards.add(card);
    //             NobleDeck.addCard(card.makeCopy());
    //         }
    //     }
    //     masterDeck.group.removeAll(nobleCards);
    // }

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
        FgoNpWidth = hb.width * (fgoNp - base) / 100.0F;
    }

    public void renderHealth(SpriteBatch sb) {
        super.renderHealth(sb);
        float x = hb.x;
        float y = hb.y + hb.height;
        FgoNPhb = new Hitbox(x, y, hb_w, FgoNp_BAR_HEIGHT);
        FgoNPhb.render(sb);
        TruthValueBgRender(sb, x, y);
        renderTruthValueBar(sb, x);
        TruthValueText(sb);

        FgoNPhb.update();
        if (FgoNPhb.hovered) {
            if (!AbstractDungeon.isScreenUp) {
                TipHelper.renderGenericTip((float)(hb.cX + hb.width / 2.0f + TIP_OFFSET_R_X), (float)(y + hb.height / 2.0f), (String)NPTEXT[0], (String)NPTEXT[1]);
            }
            FgoNpHideTimer -= Gdx.graphics.getDeltaTime() * 4.0f;
            if (FgoNpHideTimer < 0.2f) {
                FgoNpHideTimer = 0.2f;
            }
        } else {
            FgoNpHideTimer += Gdx.graphics.getDeltaTime() * 4.0f;
            if (FgoNpHideTimer > 1.0f) {
                FgoNpHideTimer = 1.0f;
            }
        }
    }

    private void renderTruthValueBar(SpriteBatch sb, float x) {
        Color color = fgoNp > 200 ? FgoNpBarColor3 : (fgoNp > 100 ? FgoNpBarColor2 : FgoNpBarColor1);
        sb.setColor(color);

        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, FgoNpWidth, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + FgoNpWidth, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }

    private void TruthValueText(SpriteBatch sb) {
        float tmp = FgoNptextColor.a;
        FgoNptextColor.a *= FgoNpHideTimer;
        FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, Master.fgoNp + "%", hb.cX, FgoNPhb.cY, FgoNptextColor);
        FgoNptextColor.a = tmp;
    }

    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        Color color = fgoNp > 200 ? FgoNpBarColor2 : (fgoNp > 100 ? FgoNpBarColor1 : FgoNpShadowColor);
        sb.setColor(color);

        //宝具值外框颜色。
        sb.draw(ImageMaster.HB_SHADOW_L, x - FgoNp_BAR_HEIGHT, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_B, x, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_R, x + hb.width, FgoNPhb.cY - FgoNp_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.setColor(FgoNpBgColor);
        //宝具值内框灰色。
        sb.draw(ImageMaster.HEALTH_BAR_L, x - FgoNp_BAR_HEIGHT, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, hb.width, FgoNp_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + hb.width, y, 20.0F * Settings.scale, FgoNp_BAR_HEIGHT);
    }
}
