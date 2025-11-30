package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ChasmatisPower extends BasePower {
    public static final String POWER_ID = makeID(ChasmatisPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    public ChasmatisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "OriginBulletPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner == owner && damageAmount > 0) {
            flash();
            addToBot(new DrawCardAction(amount));
        }
    }
    
}
