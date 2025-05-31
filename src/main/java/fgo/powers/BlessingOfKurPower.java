package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import fgo.cards.noblecards.KurKigalIrkalla;
import fgo.patches.Enum.CardTagsEnum;

import static fgo.FGOMod.makeID;

public class BlessingOfKurPower extends BasePower {
    public static final String POWER_ID = makeID(BlessingOfKurPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BlessingOfKurPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }

    @Override
    public void updateDescription() {description = DESCRIPTIONS[0];}

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
       if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            flash();
            if (card.cardID.equals(KurKigalIrkalla.ID)) {
                addToBot(new ApplyPowerAction(owner, owner, new MaxHPPower(owner, amount * 2), amount * 2));
            } else {
                addToBot(new ApplyPowerAction(owner, owner, new MaxHPPower(owner, amount), amount));
            }
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }

    
}
