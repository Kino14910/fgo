package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class EternalSleepPower extends BasePower {
    public static final String POWER_ID = makeID(EternalSleepPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public EternalSleepPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }

    /*public void atStartOfTurn() {
        this.flash();
        this.addToBot(new PressEndTurnButtonAction());
    }*/

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractPower power : this.owner.powers) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
            break;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    
}
