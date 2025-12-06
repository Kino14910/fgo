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
        if (amount <= 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = String.format(DESCRIPTIONS[1], amount);
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            return 0;
        }

        return damageAmount;
    }

    
}
