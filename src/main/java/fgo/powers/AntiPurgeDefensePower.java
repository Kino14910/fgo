package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class AntiPurgeDefensePower extends BasePower {
    public static final String POWER_ID = makeID(AntiPurgeDefensePower.class.getSimpleName());

    public AntiPurgeDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
    }

    @Override
    public void updateDescription() {
        this.description = (amount <= 1)
        ? DESCRIPTIONS[0]
        : String.format(DESCRIPTIONS[1], amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToBot(new HealAction(this.owner, this.owner, 5));
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }

        return 0;
    }

    
}
