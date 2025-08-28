package fgo.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fgo.action.lor.ChangeSceneEffect;
import fgo.cards.noblecards.Unlimited;
import fgo.characters.Master;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.monster.StarGainMonsterPower;
import fgo.util.Sounds;

import static fgo.FGOMod.makeID;

public class Emiya extends AbstractMonster {
    public static final String ID = makeID(Emiya.class.getSimpleName());
    public static final String IMG = "fgo/images/monster/emiya.png";
    public static final String IMG2 = "fgo/images/monster/emiya_Ver2_Stage3.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("fgo:Emiya");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    
    // Move constants - 参考AwakenedOne的编号方式
    private static final byte SLASH = 1;
    private static final byte MULTI_SLASH = 2;
    private static final byte REBIRTH = 3; // 复活技能
    private static final byte PROJECTION = 5;
    private static final byte MIND_EYE = 6;
    private static final byte MAGE_CRAFT = 8;
    
    // Damage values
    private static final int SLASH_DMG = 16;
    private static final int A4_SLASH_DMG = 20;
    private static final int MULTI_SLASH_DMG = 10;
    private static final int A4_MULTI_SLASH_DMG = 12;
    private static final int PROJECTION_DMG = 1;
    private static final int A4_PROJECTION_DMG = 2;
    
    // Other values
    private static final int HIT_NUM = 2;
    private static final int SWORD_AMT = 1;
    private static final int PROJECTION_ATK_AMT = 10;
    private static final int BASE_STR_AMT = 1;
    private static final int A19_STR_AMT = 2;
    private static final int BASE_BLOCK_AMT = 10;
    private static final int A19_BLOCK_AMT = 15;
    private static final int BASE_HP = 300;
    private static final int A9_HP = 320;
    private static final int REBIRTH_HP = 300;
    private static final int A9_REBIRTH_HP = 320;
    
    private int moveCount = 0;
    private boolean form1 = true;
    private boolean firstTurn = true;
    private boolean secondTurn = true;
    private boolean halfDead = false;
    
    public Emiya() {
        this(0.0F, 0.0F);
    }
    
    public Emiya(float x, float y) {
        super(NAME, ID, BASE_HP, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        setHp(AbstractDungeon.ascensionLevel < 9 ? BASE_HP : A9_HP);

        int slashDmg;
        int multiSlashDmg;
        int projectionDmg;

        if (AbstractDungeon.ascensionLevel < 4) {
            slashDmg = SLASH_DMG;
            multiSlashDmg = MULTI_SLASH_DMG;
            projectionDmg = PROJECTION_DMG;
        } else {
            slashDmg = A4_SLASH_DMG;
            multiSlashDmg = A4_MULTI_SLASH_DMG;
            projectionDmg = A4_PROJECTION_DMG;
        }
        
        this.damage.add(new DamageInfo(this, slashDmg));
        this.damage.add(new DamageInfo(this, multiSlashDmg));
        this.damage.add(new DamageInfo(this, projectionDmg));
    }

    public void usePreBattleAction() {
        // 可以添加一些战斗前的动作
    }

    public void takeTurn() {
        switch (nextMove) {
            case SLASH:
                addToBot(new DamageAction(AbstractDungeon.player, damage.get(0), AbstractGameAction.AttackEffect.SMASH));
                break;
            case MULTI_SLASH:
                // addToBot(new SFXAction("S011_Attack6"));
                addToBot(new SFXAction(Sounds.S011_Attack6));
                for(int i = 0; i < HIT_NUM; ++i) {
                    addToBot(new DamageAction(AbstractDungeon.player, damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
                addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 
                    AbstractDungeon.ascensionLevel >= 19 ? A19_STR_AMT : BASE_STR_AMT)));
                break;
            case REBIRTH:
                CardCrawlGame.music.silenceTempBgmInstantly();
                CardCrawlGame.music.silenceBGMInstantly();
                addToBot(new SFXAction(Sounds.UBW_Incantation));
                // addToBot(new WaitFgoAction(1.0F));
                // addToBot(new WaitAction(1.0F));
                addToBot(new HealAction(this, this, 
                    AbstractDungeon.ascensionLevel >= 9 ? A9_REBIRTH_HP : REBIRTH_HP));
                form1 = false;
                halfDead = false;
                firstTurn = true;
                AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
                addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage("fgo/images/vfx/UnlimitedBg.png"))));
                CardCrawlGame.music.unsilenceBGM();
                AbstractDungeon.scene.fadeOutAmbiance();
                AbstractDungeon.getCurrRoom().playBgmInstantly("UBW_Extended.mp3");
                img = ImageMaster.loadImage(IMG2);
                break;
            case PROJECTION:
                addToBot(new TalkAction(this, DIALOG[0], 2.5F, 2.5F));
                addToBot(new SFXAction(Sounds.S011_Skill1));
                addToBot(new WaitAction(0.25F));
                for (int i = 0; i < PROJECTION_ATK_AMT; i++) {
                    addToBot(new DamageAction(AbstractDungeon.player, damage.get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    addToBot(new MakeTempCardInDiscardAction(AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy(), SWORD_AMT));
                }
                addToBot(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 1), 1));
                addToBot(new GainBlockAction(this, this, 
                    AbstractDungeon.ascensionLevel >= 19 ? A19_BLOCK_AMT : BASE_BLOCK_AMT));
                addToBot(new ApplyPowerAction(this, this, new StarGainMonsterPower(this, 20), 20));
                break;
            case MIND_EYE:
                addToBot(new TalkAction(this, DIALOG[1], 2.5F, 2.5F));
                addToBot(new SFXAction(Sounds.S011_Skill2));
                addToBot(new WaitAction(0.25F));
                addToBot(new ApplyPowerAction(this, this, new CriticalDamageUpPower(this, 25), 25));
                addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 
                    AbstractDungeon.ascensionLevel >= 19 ? A19_STR_AMT : BASE_STR_AMT)));
                break;
            case MAGE_CRAFT:
                addToBot(new TalkAction(this, DIALOG[2], 2.5F, 2.5F));
                addToBot(new SFXAction(Sounds.S011_Skill3));
                addToBot(new WaitAction(0.25F));
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 99, true), 99));
                break;
        }

        addToBot(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if (form1) {
            // 第一阶段逻辑
            if (firstTurn) {
                setMove(SLASH, Intent.ATTACK, damage.get(0).base);
                firstTurn = false;
                return;
            }

             if (num < 25) {
                if (!lastTwoMoves(SLASH)) {
                    setMove(SLASH, Intent.ATTACK, damage.get(0).base);
                } else {
                    setMove(MULTI_SLASH, Intent.ATTACK, damage.get(1).base, HIT_NUM, true);
                }
            } else {
                if (!lastMove(MULTI_SLASH)) {
                    setMove(MULTI_SLASH, Intent.ATTACK, damage.get(1).base, HIT_NUM, true);
                } else {
                    setMove(SLASH, Intent.ATTACK, damage.get(0).base);
                }
            }
        } else {
            // 第二阶段逻辑
            if (firstTurn) {
                setMove(MOVES[0], PROJECTION, Intent.ATTACK_BUFF,damage.get(2).base, PROJECTION_ATK_AMT, true);
                firstTurn = false;
                return;
            }
            if (secondTurn){
                setMove(MOVES[3], MAGE_CRAFT, Intent.DEBUFF);
                return;
            }
            // 第二阶段技能选择 - 按特定顺序使用
            switch (moveCount % 3) {
                case 0:
                    setMove(MOVES[1], MIND_EYE, Intent.BUFF);
                    break;
                case 1:
                    setMove(SLASH, Intent.ATTACK, damage.get(0).base);
                    break;
                case 2:
                    setMove(MULTI_SLASH, Intent.ATTACK, damage.get(1).base, HIT_NUM, true);
                    break;
            }
        }
        
        ++moveCount;
    }
    
    @Override
    public void damage(DamageInfo info) {
        super.damage(info);
        
        // 检查是否需要触发复活
        if (currentHealth <= 0 && !halfDead && form1) {
            halfDead = true;
            setMove(REBIRTH, Intent.UNKNOWN);
            createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, REBIRTH, Intent.UNKNOWN));
            applyPowers();
        }
    }

    public void die() {
        // 只有在第二阶段死亡才会真正死亡
        if (!form1) {
            super.die();
            if (!CardHelper.hasCardWithID(Unlimited.ID) && AbstractDungeon.player instanceof Master) {
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Unlimited(), 
                    (float)Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }
}