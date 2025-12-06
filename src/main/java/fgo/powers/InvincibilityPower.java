package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class InvincibilityPower extends BasePower {
    public static final String POWER_ID = makeID(InvincibilityPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public InvincibilityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = formatDescriptionByQuantity(amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            addToTop(new ReducePowerAction(owner, owner, ID, 1));
            return 0;
        }

        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

}
