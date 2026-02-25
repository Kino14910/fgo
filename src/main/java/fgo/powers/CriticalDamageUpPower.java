package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class CriticalDamageUpPower extends BasePower {
    public static final String POWER_ID = makeID(CriticalDamageUpPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    /**
     * {@link StarPower#finalDamage(float, DamageType, float)}
     */
    public CriticalDamageUpPower(AbstractCreature owner, int amount) {
         super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }
}
