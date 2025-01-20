package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.patches.Enum.CardTagsEnum;


import static fgo.FGOMod.makeID;

public class StarGainPower extends BasePower {
    public static final String POWER_ID = makeID(StarGainPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StarGainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        if (this.amount >= 10) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //你有10颗暴击星时才能暴击。
        if (!card.hasTag(CardTagsEnum.Noble_Phantasm) && this.amount >= 10) {
            //特例：翡翠的魅力、20暴击星
            if (card.cardID.equals("CharismaOfTheJade") && this.amount >= 20) {
                return this.atDamageGive2(damage, type);
            }
            return this.atDamageGive(damage, type);
        }
        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (this.owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (200.0F + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    //翡翠的魅力暴击伤害。
    public float atDamageGive2(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (this.owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (300.0F + CrAmt + CrAmt / 2.0F) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在你打出攻击牌时，且有10颗以上暴击星，且不是宝具牌。
        if (card.type == AbstractCard.CardType.ATTACK && this.amount >= 10 && !card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            if (!this.owner.hasPower(CrossingArcadiaPower.POWER_ID)) {
                if (card.cardID.equals("CharismaOfTheJade") && this.amount >= 20) {
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 20));
                } else {
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 10));
                }
            }
            //在有十二辉剑时，有卡牌暴击时获得一层十二辉剑效果。
            if (this.owner.hasPower(HeroicKingPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new HeroicKingPower(this.owner, 1), 1));
            }
            if (this.owner.hasPower(LoseCritDamagePower.POWER_ID)) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, LoseCritDamagePower.POWER_ID));
            }
        }
    }

    public AbstractPower makeCopy() {return new StarGainPower(this.owner, this.amount);}
}
