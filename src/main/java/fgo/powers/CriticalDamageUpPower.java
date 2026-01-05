package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class CriticalDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(CriticalDamageUpPower.class.getSimpleName());

    /**
     * {@link StarPower#finalDamage(float, DamageType, float)}
     */
    public CriticalDamageUpPower(AbstractCreature owner, int amount) {
         super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
