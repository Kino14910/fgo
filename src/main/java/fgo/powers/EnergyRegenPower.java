package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

import fgo.action.FgoNpAction;

public class EnergyRegenPower extends BasePower {
    public static final String POWER_ID = makeID(EnergyRegenPower.class.getSimpleName());

    public EnergyRegenPower(AbstractCreature owner, int npAmount) {
        super(POWER_ID, PowerType.BUFF, false, owner, npAmount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new FgoNpAction(amount));
    }
}
