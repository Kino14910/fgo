package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class GoddessMetamorphosisBeastPower extends BasePower {
    public static final String POWER_ID = makeID(GoddessMetamorphosisBeastPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public GoddessMetamorphosisBeastPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "BeastPower");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != owner && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            addToBot(new ApplyPowerAction(target, owner, new PoisonPower(target, owner, amount),
                    amount, true));
            addToBot(new ApplyPowerAction(target, owner, new CursePower(target, owner, 1), 1, true));
        }
    }

}
