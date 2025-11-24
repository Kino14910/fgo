package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ItsInevitablePower extends BasePower {
    public static final String POWER_ID = makeID(ItsInevitablePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ItsInevitablePower(AbstractCreature owner, int amount, int boost) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BurningPower");
        this.amount2 += boost;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, amount2);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        amount += amount2;
        addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        updateDescription();
    }

    
}
