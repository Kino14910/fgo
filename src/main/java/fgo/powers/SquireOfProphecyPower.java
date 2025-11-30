package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SquireOfProphecyPower extends BasePower {
    public static final String POWER_ID = makeID(SquireOfProphecyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public SquireOfProphecyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount, "FightToDeathPower");
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (EnergyPanel.totalCount == 0 && !this.owner.hasPower("fgo:NoPrayerForRainPower")) {
            this.flash();
            this.addToBot(new GainEnergyAction(2));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new NoPrayerForRainPower(this.owner), 1));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    
}