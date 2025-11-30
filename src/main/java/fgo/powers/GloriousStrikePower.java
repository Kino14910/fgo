package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class GloriousStrikePower extends BasePower {
    public static final String POWER_ID = makeID(GloriousStrikePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public GloriousStrikePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, false, owner);
    }
    
    // 重写父类的updateDescription方法，用于更新卡牌描述
    @Override
    public void updateDescription() {
        this.description = this.amount <= 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToTop(new RemoveDebuffsAction(owner));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
