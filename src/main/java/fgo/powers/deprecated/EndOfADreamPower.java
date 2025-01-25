package fgo.powers.deprecated;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.powers.BasePower;
import fgo.powers.EternalSleepPower;

import static fgo.FGOMod.makeID;

public class EndOfADreamPower extends BasePower {
    public static final String POWER_ID = makeID(EndOfADreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public EndOfADreamPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner); 

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        //this.addToBot(new RemoveAllPowersAction(this.owner, false));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new EternalSleepPower(this.owner)));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

//    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {
        return new EndOfADreamPower(this.owner);
    }
}
