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

    public SquireOfProphecyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount, "FightToDeathPower");
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (EnergyPanel.totalCount == 0 && !owner.hasPower("fgo:NoPrayerForRainPower")) {
            flash();
            addToBot(new GainEnergyAction(2));
            addToBot(new ApplyPowerAction(owner, owner, new NoPrayerForRainPower(owner), 1));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void updateDescription() {
        description = formatDescriptionByQuantity(amount);
    }

    
}