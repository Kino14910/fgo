package fgo.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import static fgo.FGOMod.makeID;

public class IgnoresInvincibilityPower extends BasePower {
    public static final String POWER_ID = makeID(IgnoresInvincibilityPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public IgnoresInvincibilityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); 
    }

    @Override
    public void updateDescription() {description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];}

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageType.NORMAL && amount > 0 ? damage * amount : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK) {
                flash();
                addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }
    }
}
