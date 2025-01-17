package fgo.action;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.BattleHymnPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import com.megacrit.cardcrawl.powers.watcher.OmegaPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fgo.powers.EvasionPower;

public class StarBasketAction extends AbstractGameAction {
    private final AbstractPlayer p;
    public StarBasketAction() {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        int roll = MathUtils.random(44);
        if (roll == 0) {//临时力量
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 2), 2));
        } else if (roll == 1) {//临时敏捷
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
            this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, 2), 2));
        } else if (roll == 2) {//力量
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        } else if (roll == 3) {//敏捷
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        } else if (roll == 4) {//多层护甲
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 2), 2));
        } else if (roll == 5) {//再生
            this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, 2), 2));
        } else if (roll == 6) {//荆棘
            this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 2), 2));
        } else if (roll == 7) {//活力
            this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, 2), 2));
        } else if (roll == 8) {//无实体
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
        } else if (roll == 9) {//人工制品
            this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
        } else if (roll == 10) {//余像
            this.addToBot(new ApplyPowerAction(p, p, new AfterImagePower(p, 1), 1));
        } else if (roll == 11) {//增幅--下一张能力牌打出2次
            this.addToBot(new ApplyPowerAction(p, p, new AmplifyPower(p, 1), 1));
        } else if (roll == 12) {//爆发--下一张技能牌打出2次
            this.addToBot(new ApplyPowerAction(p, p, new BurstPower(p, 1), 1));
        } else if (roll == 13) {//双发--下一张攻击牌打出2次
            this.addToBot(new ApplyPowerAction(p, p, new DoubleTapPower(p, 1), 1));
        } else if (roll == 14) {//闪避
            this.addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, 1), 1));
        } else if (roll == 15) {//复制--下一张牌打出2次
            this.addToBot(new ApplyPowerAction(p, p, new DuplicationPower(p, 1), 1));
        } else if (roll == 16) {//狂暴--回合开始获得能量
            this.addToBot(new ApplyPowerAction(p, p, new BerserkPower(p, 1), 1));
        } else if (roll == 17) {//残影
            this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        } else if (roll == 18) {//残暴--回合开始抽牌
            this.addToBot(new ApplyPowerAction(p, p, new BrutalityPower(p, 1), 1));
        } else if (roll == 19) {//缓冲
            this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1), 1));
        } else if (roll == 20) {//自燃--回合开始对所有人造成伤害
            this.addToBot(new ApplyPowerAction(p, p, new CombustPower(p, 1, 4), 4));
        } else if (roll == 21) {//创造性AI
            this.addToBot(new ApplyPowerAction(p, p, new CreativeAIPower(p, 1), 1));
        } else if (roll == 22) {//黑暗之拥
            this.addToBot(new ApplyPowerAction(p, p, new DarkEmbracePower(p, 1), 1));
        } else if (roll == 23) {//恶魔形态
            this.addToBot(new ApplyPowerAction(p, p, new DemonFormPower(p, 2), 2));
        } else if (roll == 24) {//幻影杀手
            this.addToBot(new ApplyPowerAction(p, p, new PhantasmalPower(p, 1), 1));
        } else if (roll == 25) {//双倍伤害
            this.addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), 1));
        } else if (roll == 26) {//下一回合抽牌
            this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
        } else if (roll == 27) {//回响形态
            this.addToBot(new ApplyPowerAction(p, p, new EchoPower(p, 1), 1));
        } else if (roll == 28) {//下一回合获得能量B
            this.addToBot(new ApplyPowerAction(p, p, new EnergizedBluePower(p, 1), 1));
        } else if (roll == 29) {//下一回合获得能量G
            this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
        } else if (roll == 30) {//涂毒
            this.addToBot(new ApplyPowerAction(p, p, new EnvenomPower(p, 1), 1));
        } else if (roll == 31) {//金属化
            this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, 2), 2));
        } else if (roll == 32) {//免费攻击
            this.addToBot(new ApplyPowerAction(p, p, new FreeAttackPower(p, 1), 1));
        } else if (roll == 33) {//欧米伽
            this.addToBot(new ApplyPowerAction(p, p, new OmegaPower(p, 3), 3));
        } else if (roll == 34) {//必备工具
            this.addToBot(new ApplyPowerAction(p, p, new ToolsOfTheTradePower(p, 1), 1));
        } else if (roll == 35) {//战歌
            this.addToBot(new ApplyPowerAction(p, p, new BattleHymnPower(p, 1), 1));
        } else if (roll == 36) {//毒雾
            this.addToBot(new ApplyPowerAction(p, p, new NoxiousFumesPower(p, 2), 2));
        } else if (roll == 37) {//神气制胜
            this.addToBot(new ApplyPowerAction(p, p, new PanachePower(p, 5), 5));
        } else if (roll == 38) {//钢笔尖
            this.addToBot(new ApplyPowerAction(p, p, new PenNibPower(p, 1), 1, true));
        } else if (roll == 39) {//愤怒--打出攻击牌获得格挡
            this.addToBot(new ApplyPowerAction(p, p, new RagePower(p, 2), 2));
        } else if (roll == 40) {//弹回
            this.addToBot(new ApplyPowerAction(p, p, new ReboundPower(p), 1));
        } else if (roll == 41) {//保留卡牌
            this.addToBot(new ApplyPowerAction(p, p, new RetainCardPower(p, 1), 1));
        } else if (roll == 42) {//残虐
            this.addToBot(new ApplyPowerAction(p, p, new SadisticPower(p, 3), 3));
        } else if (roll == 43) {//凌迟
            this.addToBot(new ApplyPowerAction(p, p, new ThousandCutsPower(p, 1), 1));
        } else {//乱战
            this.addToBot(new ApplyPowerAction(p, p, new MayhemPower(p, 1), 1));
        }

        this.isDone = true;
    }
}
