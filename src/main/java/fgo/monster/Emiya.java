package fgo.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
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
import fgo.action.WaitFgoAction;
import fgo.action.lor.ChangeSceneEffect;
import fgo.cards.noblecards.Unlimited;
import fgo.characters.Master;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.monster.StarGainMonsterPower;

import static fgo.FGOMod.makeID;

public class Emiya extends AbstractMonster {
    public static final String ID = makeID(Emiya.class.getSimpleName());
    public static final String IMG = "fgo/images/monster/Emiya.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("fgo:Emiya");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private final int HitNum;
    private final int strengthAmt;
    private final int blockAmt;
    private final int ShivAmt;
    private int moveCount = 0;
    public Emiya() {this(0.0F, 0.0F);}
    public Emiya(float x, float y) {
        super(NAME, ID, 360, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(360);
        } else {
            setHp(330, 340);
        }

        int dmg1;
        int dmg2;

        if (AbstractDungeon.ascensionLevel >= 4) {
            dmg1 = 12;
            dmg2 = 4;
        } else {
            dmg1 = 10;
            dmg2 = 3;
        }

        this.HitNum = 3;
        this.ShivAmt = 2;
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.strengthAmt = 3;
            this.blockAmt = 15;
        } else {
            this.strengthAmt = 2;
            this.blockAmt = 10;
        }
        this.damage.add(new DamageInfo(this, dmg1));
        this.damage.add(new DamageInfo(this, dmg2));

    }

    public void usePreBattleAction() {}

    public void takeTurn() {
        int i;
        switch (this.nextMove) {
            //常规攻击。
            case 1:
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                this.addToBot(new MakeTempCardInDiscardAction(new Shiv(), this.ShivAmt));
                break;
            case 2:
                for(i = 0; i < this.HitNum; ++i) {
                    this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
                if (AbstractDungeon.ascensionLevel >= 19) {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                }
                break;
            case 3:
                this.addToBot(new TalkAction(this, DIALOG[0], 2.5F, 2.5F));
                this.addToBot(new SFXAction("S011_Skill1"));
                this.addToBot(new WaitAction(0.25F));
                this.addToBot(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 2), 2));
                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.strengthAmt), this.strengthAmt));
                this.addToBot(new GainBlockAction(this, this, this.blockAmt));
                this.addToBot(new ApplyPowerAction(this, this, new StarGainMonsterPower(this, 40), 40));
                break;
            case 4:
                this.addToBot(new TalkAction(this, DIALOG[1], 2.5F, 2.5F));
                this.addToBot(new SFXAction("S011_Skill2"));
                this.addToBot(new WaitAction(0.25F));
                this.addToBot(new ApplyPowerAction(this, this, new CriticalDamageUpPower(this, 50), 50));
                break;
            case 5:
                this.addToBot(new TalkAction(this, DIALOG[2], 2.5F, 2.5F));
                this.addToBot(new SFXAction("S011_Skill3"));
                this.addToBot(new WaitAction(0.25F));
                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.strengthAmt), this.strengthAmt));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 99, true), 99));
                break;
            case 6:
                AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
                this.addToBot(new WaitFgoAction(1.0F));
                this.addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage("fgo/images/vfx/UnlimitedBg.png"))));
                this.addToBot(new WaitAction(2.5F));
                CardCrawlGame.music.silenceTempBgmInstantly();
                CardCrawlGame.music.silenceBGMInstantly();
                AbstractDungeon.getCurrRoom().playBgmInstantly("UBW_Extended.mp3");
                break;
        }

        this.addToBot(new RollMoveAction(this));
    }

    protected void getMove(int i) {
        switch (this.moveCount) {
            case 2:
                this.setMove((byte)6, Intent.UNKNOWN);
                break;
            case 4:
                this.setMove(MOVES[0], (byte)3, Intent.BUFF);
                break;
            case 5:
                this.setMove(MOVES[1], (byte)4, Intent.BUFF);
                break;
            case 8:
                this.setMove(MOVES[2], (byte)5, Intent.BUFF);
                break;
            default:
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    this.setMove((byte)2, Intent.ATTACK, this.damage.get(1).base, this.HitNum, true);
                } else {
                    this.setMove((byte)1, Intent.ATTACK, this.damage.get(0).base);
                }
        }

        ++this.moveCount;
    }

    public void die() {
        super.die();
        //CardCrawlGame.sound.play("CHOSEN_DEATH");
        //如果你没有无限剑制。
        if (!CardHelper.hasCardWithID(Unlimited.ID) && AbstractDungeon.player instanceof Master) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Unlimited(), (float)Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
        }
    }
}