package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class EvasionPower extends BasePower {
    public static final String POWER_ID = makeID(EvasionPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public EvasionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount); 
    }

    @Override
    public void updateDescription() {
        description = formatDescriptionByQuantity(amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            addToTop(new ReducePowerAction(owner, owner, ID, 1));
            return 0;
        }

        return damageAmount;
    }

    
}
