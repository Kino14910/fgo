package fgo.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static fgo.FGOMod.makeID;

public class MaxHPPower extends BasePower {
    public static final String POWER_ID = makeID(MaxHPPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MaxHPPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.decreaseMaxHealth(this.amount);
    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.decreaseMaxHealth(this.amount);
    }

    
}
