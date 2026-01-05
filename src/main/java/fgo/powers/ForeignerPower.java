package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ForeignerPower extends BasePower {
    public static final String POWER_ID = makeID(ForeignerPower.class.getSimpleName());

    /**
     * {@link StarPower#atDamageGive(float, DamageType, AbstractCard)}
     */
    public ForeignerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
    }
}
