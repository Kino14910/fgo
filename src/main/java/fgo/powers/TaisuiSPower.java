package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TaisuiSPower extends BasePower {
    public static final String POWER_ID = makeID(TaisuiSPower.class.getSimpleName());

    public TaisuiSPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount, "EndOfADreamPower");
    }

    @Override
    public void onDeath() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && owner.currentHealth <= 0) {
            flash();
            addToBot(new GainEnergyAction(amount));
        }
    }
}
