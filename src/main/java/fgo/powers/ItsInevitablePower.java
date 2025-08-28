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
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.boost == 0) {
            this.description = String.format(DESCRIPTIONS[0], this.amount);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.boost);
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.boost, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        this.amount += this.boost;
        // this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        updateDescription();
    }

    
}
