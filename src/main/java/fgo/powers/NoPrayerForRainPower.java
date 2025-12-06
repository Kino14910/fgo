package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class NoPrayerForRainPower extends BasePower {
    public static final String POWER_ID = makeID(NoPrayerForRainPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public NoPrayerForRainPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, false, owner, "PutOnFakeFacePower");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    
}
