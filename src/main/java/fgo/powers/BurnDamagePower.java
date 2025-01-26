package fgo.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class BurnDamagePower extends BasePower {
    public static final String POWER_ID = makeID(BurnDamagePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public BurnDamagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void atStartOfTurn() {
        if (!this.owner.hasPower(ItsInevitablePower.POWER_ID)) {
            //燃烧时你不受到灼烧伤害。
            this.flash();
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    
}
