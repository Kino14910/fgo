package fgo.powers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class PutOnFakeFacePower extends BasePower {
    public static final String POWER_ID = makeID(PutOnFakeFacePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PutOnFakeFacePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        int roll = MathUtils.random(2);
        if (roll == 0) {
            this.addToBot(new GainEnergyAction(1));
        } else if (roll == 1) {
            this.addToBot(new HealAction(this.owner, this.owner, 5));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "CursePower", 3));
        }
    }

    public AbstractPower makeCopy() {return new PutOnFakeFacePower(this.owner);}
}
