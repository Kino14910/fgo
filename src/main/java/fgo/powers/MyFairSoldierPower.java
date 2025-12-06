package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MyFairSoldierPower extends BasePower {
    public static final String POWER_ID = makeID(MyFairSoldierPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;

    public MyFairSoldierPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount, "SkillSeal");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }

    @Override
    public void onRemove() {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -amount), -amount));
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -amount), -amount));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    
}
