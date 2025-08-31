package fgo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import static fgo.FGOMod.makeID;

public class ItsInevitablePower extends BasePower {
    public static final String POWER_ID = makeID(ItsInevitablePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int boost;

    public ItsInevitablePower(AbstractCreature owner, int amount, int boost) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BurningPower");
        this.boost = boost;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (boost == 0) {
            description = String.format(DESCRIPTIONS[0], amount);
        } else {
            description = String.format(DESCRIPTIONS[1], amount, boost);
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        amount += boost;
        // addToBot(new ReducePowerAction(owner, owner, ID, 1));
        updateDescription();
    }

    
}
