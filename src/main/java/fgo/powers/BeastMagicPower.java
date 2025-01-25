package fgo.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class BeastMagicPower extends BasePower {
    public static final String POWER_ID = makeID(BeastMagicPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BeastMagicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "DefenseUpPower");
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == this.owner && target == this.owner) {
            this.flash();
            this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        }
    }

    public AbstractPower makeCopy() {
        return new BeastMagicPower(this.owner, this.amount);
    }
}
