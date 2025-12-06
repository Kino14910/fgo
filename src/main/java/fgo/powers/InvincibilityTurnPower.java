package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class InvincibilityTurnPower extends BasePower {
    public static final String POWER_ID = makeID(InvincibilityTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public InvincibilityTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "InvincibilityPower");
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            return 0;
        }

        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

}
