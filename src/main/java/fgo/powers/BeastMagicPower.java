package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BeastMagicPower extends BasePower {
    public static final String POWER_ID = makeID(BeastMagicPower.class.getSimpleName());

    public BeastMagicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "DefenseUpPower");
    }
    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target == owner && power.type == AbstractPower.PowerType.DEBUFF) {
            flash();
            addToBot(new GainBlockAction(owner, owner, amount));
        }
    }
}
