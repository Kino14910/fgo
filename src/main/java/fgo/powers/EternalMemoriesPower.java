package fgo.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class EternalMemoriesPower extends BasePower {
    public static final String POWER_ID = makeID(EternalMemoriesPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EternalMemoriesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToBot(new HealAction(this.owner, this.owner, 5));
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }

        return 0;
    }

    public AbstractPower makeCopy() {
        return new EternalMemoriesPower(this.owner, this.amount);
    }
}
