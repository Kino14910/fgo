package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MonteCristoTreasurePower extends BasePower {
    public static final String POWER_ID = makeID(MonteCristoTreasurePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public MonteCristoTreasurePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "FightToDeathPower");
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != owner && info.type == DamageInfo.DamageType.NORMAL && owner.hasPower("fgo:StarPower") && owner.getPower("fgo:StarPower").amount >= 10) {
            flash();
            addToBot(new GainBlockAction(owner, owner, damageAmount * amount));
        }
    }
}
