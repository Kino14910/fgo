package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import fgo.patches.Enum.CardTagsEnum;

import static fgo.FGOMod.makeID;

public class StarPower extends BasePower {
    public static final String POWER_ID = makeID(StarPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StarPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount >= 10) {
            description = DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0];
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //你有10颗暴击星时才能暴击。
        if (!card.hasTag(CardTagsEnum.Noble_Phantasm) && amount >= 10) {
            return finalDamage(damage, type, 1.0F);
        }

        return damage;
    }


    public float finalDamage(float damage, DamageInfo.DamageType type, float multiplier) {
        //暴击威力提高。
        if (owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (200.0F + CrAmt) / 100.0F * multiplier : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * multiplier : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在你打出攻击牌时，且有10颗以上暴击星，且不是宝具牌。
        if (card.type == AbstractCard.CardType.ATTACK && amount >= 10 && !card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            if (owner.hasPower(CrossingArcadiaPower.POWER_ID)) {
                return;
            }
            addToBot(new ReducePowerAction(owner, owner, ID, card.cardID.equals("CharismaOfTheJade") && amount >= 20 ? 20 : 10));

            //在有十二辉剑时，有卡牌暴击时获得一层十二辉剑效果。
            if (owner.hasPower(HeroicKingPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(owner, owner, new HeroicKingPower(owner, 1), 1));
            }
            if (owner.hasPower(LoseCritDamagePower.POWER_ID)) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, LoseCritDamagePower.POWER_ID));
            }
        }
    }


}
