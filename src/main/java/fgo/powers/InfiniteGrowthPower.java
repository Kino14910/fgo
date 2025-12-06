package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class InfiniteGrowthPower extends BasePower {
    public static final String POWER_ID = makeID(InfiniteGrowthPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public InfiniteGrowthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount < amount && damageAmount > 0) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
            return 0;
        }

        return damageAmount;
    }

}
