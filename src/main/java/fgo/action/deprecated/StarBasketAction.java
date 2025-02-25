package fgo.action.deprecated;

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
        switch (roll) {
            case 0: //凌迟
                applyPower(p, new ThousandCutsPower(p, 1));
                break;
            case 1: //残虐
                applyPower(p, new SadisticPower(p, 3));
                break;
            case 2: //力量
                applyPower(p, new StrengthPower(p, 1));
                break;
            case 3: //敏捷
                applyPower(p, new DexterityPower(p, 1));
                break;
            case 4: //多层护甲
                applyPower(p, new PlatedArmorPower(p, 2));
                break;
            case 5: //再生
                applyPower(p, new RegenPower(p, 2));
                break;
            case 6: //荆棘
                applyPower(p, new ThornsPower(p, 2));
                break;
            case 7: //活力
                applyPower(p, new VigorPower(p, 2));
                break;
            case 8: //无实体
                applyPower(p, new IntangiblePlayerPower(p, 1));
                break;
            case 9: //人工制品
                applyPower(p, new ArtifactPower(p, 1));
                break;
            case 10: //余像
                applyPower(p, new AfterImagePower(p, 1));
                break;
            case 11: //增幅--下一张能力牌打出2次
                applyPower(p, new AmplifyPower(p, 1));
                break;
            case 12: //爆发--下一张技能牌打出2次
                applyPower(p, new BurstPower(p, 1));
                break;
            case 13: //双发--下一张攻击牌打出2次
                applyPower(p, new DoubleTapPower(p, 1));
                break;
            case 14: //闪避
                applyPower(p, new EvasionPower(p, 1));
                break;
            case 15: //复制--下一张牌打出2次
                applyPower(p, new DuplicationPower(p, 1));
                break;
            case 16: //狂暴--回合开始获得能量
                applyPower(p, new BerserkPower(p, 1));
                break;
            case 17: //残影
                applyPower(p, new BlurPower(p, 1));
                break;
            case 18: //残暴--回合开始抽牌
                applyPower(p, new BrutalityPower(p, 1));
                break;
            case 19: //缓冲
                applyPower(p, new BufferPower(p, 1));
                break;
            case 20: //自燃--回合开始对所有人造成伤害
                applyPower(p, new CombustPower(p, 1, 4));
                break;
            case 21: //创造性AI
                applyPower(p, new CreativeAIPower(p, 1));
                break;
            case 22: //黑暗之拥
                applyPower(p, new DarkEmbracePower(p, 1));
                break;
            case 23: //恶魔形态
                applyPower(p, new DemonFormPower(p, 2));
                break;
            case 24: //幻影杀手
                applyPower(p, new PhantasmalPower(p, 1));
                break;
            case 25: //双倍伤害
                applyPower(p, new DoubleDamagePower(p, 1, false));
                break;
            case 26: //下一回合抽牌
                applyPower(p, new DrawCardNextTurnPower(p, 1));
                break;
            case 27: //回响形态
                applyPower(p, new EchoPower(p, 1));
                break;
            case 28: //下一回合获得能量B
                applyPower(p, new EnergizedBluePower(p, 1));
                break;
            case 29: //下一回合获得能量G
                applyPower(p, new EnergizedPower(p, 1));
                break;
            case 30: //涂毒
                applyPower(p, new EnvenomPower(p, 1));
                break;
            case 31: //金属化
                applyPower(p, new MetallicizePower(p, 2));
                break;
            case 32: //免费攻击
                applyPower(p, new FreeAttackPower(p, 1));
                break;
            case 33: //欧米伽
                applyPower(p, new OmegaPower(p, 3));
                break;
            case 34: //必备工具
                applyPower(p, new ToolsOfTheTradePower(p, 1));
                break;
            case 35: //战歌
                applyPower(p, new BattleHymnPower(p, 1));
                break;
            case 36: //毒雾
                applyPower(p, new NoxiousFumesPower(p, 2));
                break;
            case 37: //神气制胜
                applyPower(p, new PanachePower(p, 5));
                break;
            case 38: //钢笔尖
                applyPower(p, new PenNibPower(p, 1));
                break;
            case 39: //愤怒--打出攻击牌获得格挡
                applyPower(p, new RagePower(p, 2));
                break;
            case 40: //弹回
                applyPower(p, new ReboundPower(p));
                break;
            case 41: //保留卡牌
                applyPower(p, new RetainCardPower(p, 1));
                break;
            default: //乱战
                applyPower(p, new MayhemPower(p, 1));
                break;
        }

        this.isDone = true;
        }

        private void applyPower(AbstractPlayer p, AbstractPower power) {
            this.addToBot(new ApplyPowerAction(p, p, power, power.amount));
        }

}
