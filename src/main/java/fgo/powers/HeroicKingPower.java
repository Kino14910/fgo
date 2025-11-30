package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class HeroicKingPower extends BasePower {
    public static final String POWER_ID = makeID(HeroicKingPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public HeroicKingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "PutOnFakeFacePower");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    /*
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new HeroicKingPower(this.owner, 1), 1));
        }
    }
    */

    
}
