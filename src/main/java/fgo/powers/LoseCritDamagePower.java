package fgo.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class LoseCritDamagePower extends BasePower {
    public static final String POWER_ID = makeID(LoseCritDamagePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LoseCritDamagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onRemove() {
        this.flash();
        //this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, CriticalDamageUpPower.POWER_ID));
    }

    public AbstractPower makeCopy() {
        return new LoseCritDamagePower(this.owner, this.amount);
    }
}
