package fgo.monsters;

import static fgo.FGOMod.makeID;
import static fgo.FGOMod.vfxPath;
import static fgo.utils.ModHelper.addToBotAbstract;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import fgo.FGOMod;
import fgo.action.lor.ChangeSceneEffect;
import fgo.cards.noblecards.Unlimited;
import fgo.characters.Master;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.monster.StarGainMonsterPower;
import fgo.utils.ModHelper;
import fgo.utils.Sounds;

public class Emiya extends BaseMonster {
    public static final String ID = makeID(Emiya.class.getSimpleName());
    public static final String IMG = FGOMod.monsterPath("emiya");
    public static final String IMG2 = FGOMod.monsterPath("emiya_Ver2_Stage3");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    
    // Move constants - 参考AwakenedOne的编号方式
    private static final byte CALADBOLG = 0;
    private static final byte KANSHOU = 1;
    private static final byte REBIRTH = 2; // 复活技能
    private static final byte PROJECTION = 3;
    private static final byte MIND_EYE = 4;
    private static final byte HAWK_EYE = 5;
    
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
        // Pass raw id to BaseMonster (it will call FGOMod.makeID internally)
        super(NAME, ID, BASE_HP, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        setHp(AbstractDungeon.ascensionLevel < 9 ? BASE_HP : A9_HP);

        if (ModHelper.moreDamageAscension(type)) {
            setDamages(16, 10, 1);
        } else {
            setDamages(20, 12, 2);
        }

        addMoveA(Intent.ATTACK, damage.get(0).base, mi -> {
            shout(CALADBOLG, Sounds.Kanshou);
            attack(mi, AbstractGameAction.AttackEffect.SMASH);
        });
        addMoveA(Intent.ATTACK, damage.get(1).base, HIT_NUM, mi -> {
            shout(KANSHOU, Sounds.Kanshou);
            attack(mi, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 
                AbstractDungeon.ascensionLevel >= 19 ? A19_STR_AMT : BASE_STR_AMT)));
        });
        addMove(Intent.UNKNOWN, mi -> {
            CardCrawlGame.music.silenceTempBgmInstantly();
            CardCrawlGame.music.silenceBGMInstantly();
            addToBot(new SFXAction(Sounds.UBW_Incantation));
            addToBot(new HealAction(this, this, AbstractDungeon.ascensionLevel < 9 ? REBIRTH_HP : A9_REBIRTH_HP));
            form1 = false;
            halfDead = false;
            firstTurn = true;
            AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
            addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage(vfxPath("UnlimitedBg")))));
            CardCrawlGame.music.unsilenceBGM();
            AbstractDungeon.scene.fadeOutAmbiance();
            AbstractDungeon.getCurrRoom().playBgmInstantly("UBW_Extended.mp3");
            addToBotAbstract(()->{img = ImageMaster.loadImage(IMG2);});
            
        });
        addMoveA(Intent.ATTACK_BUFF, damage.get(2).base, PROJECTION_ATK_AMT, mi -> {
            shout(0);
            addToBot(new WaitAction(0.25F));
            attack(mi, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            addToBot(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 1), 1));
            addToBot(new GainBlockAction(this, this, AbstractDungeon.ascensionLevel >= 19 ? A19_BLOCK_AMT : BASE_BLOCK_AMT));
            addToBot(new ApplyPowerAction(this, this, new StarGainMonsterPower(this, 20), 20));
        });
        addMove(Intent.BUFF, mi -> {
            shout(PROJECTION, Sounds.TraceOn);
            addToBot(new WaitAction(0.25F));
            addToBot(new ApplyPowerAction(this, this, new CriticalDamageUpPower(this, 25), 25));
            addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 
                AbstractDungeon.ascensionLevel >= 19 ? A19_STR_AMT : BASE_STR_AMT)));
        });
        addMove(Intent.DEBUFF, mi -> {
            shout(HAWK_EYE, Sounds.Konosaida);
            addToBot(new WaitAction(0.25F));
            addToBot(new ApplyPowerAction(p, this, new FrailPower(p, 99, true), 99));
        });
    }

    @Override
    public void usePreBattleAction() {
    }

    @Override
    protected void getMove(int num) {
        if (form1) {
            // 第一阶段逻辑
            if (firstTurn) {
                setMove(CALADBOLG);
                firstTurn = false;
                return;
            }

             if (num < 25) {
                if (!lastTwoMoves(CALADBOLG)) {
                    setMove(CALADBOLG);
                } else {
                    setMove(KANSHOU);
                }
            } else {
                if (!lastMove(KANSHOU)) {
                    setMove(KANSHOU);
                } else {
                    setMove(CALADBOLG);
                }
            }
        } else {
            // 第二阶段逻辑
            if (firstTurn) {
                setMove(PROJECTION);
                firstTurn = false;
                return;
            }
            if (secondTurn){
                setMove(HAWK_EYE);
                return;
            }
            // 第二阶段技能选择 - 按特定顺序使用
            switch (moveCount % 3) {
                case 0:
                    setMove(MIND_EYE);
                    break;
                case 1:
                    setMove(CALADBOLG);
                    break;
                case 2:
                    setMove(KANSHOU);
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
            setMove(REBIRTH);
            createIntent();
            addToBot(new SetMoveAction(this, REBIRTH, Intent.UNKNOWN));
            applyPowers();
        }
    }

    @Override
    public void die() {
        // 只有在第二阶段死亡才会真正死亡
        if (!form1) {
            super.die();
            if (!CardHelper.hasCardWithID(Unlimited.ID) && AbstractDungeon.player instanceof Master) {
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Unlimited(), 
                    (float)Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
            }
            onBossVictoryLogic();
        }
    }
}