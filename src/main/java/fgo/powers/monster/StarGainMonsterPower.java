package fgo.powers.monster;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.powers.BasePower;
import fgo.powers.CriticalDamageUpPower;

public class StarGainMonsterPower extends BasePower {
    public static final String POWER_ID = makeID(StarGainMonsterPower.class.getSimpleName());

    public StarGainMonsterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {description = DESCRIPTIONS[0];}

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (amount >= 10) {
            return atDamageGive(damage, type);
        }
        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (owner.hasPower(CriticalDamageUpPower.POWER_ID)) {
            int CrAmt = (owner.getPower(CriticalDamageUpPower.POWER_ID)).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (200.0F + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
       flash();
       addToTop(new ReducePowerAction(owner, owner, ID, 10));
    }

    
}
