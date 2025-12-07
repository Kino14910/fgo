package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.CustomEnums.FGOCardColor;

public class StarHunterPower extends BasePower {
    public static final String POWER_ID = makeID(StarHunterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public StarHunterPower(AbstractCreature owner, int times, int amount2) {
        super(POWER_ID, TYPE, false, owner, times, "CriticalDamageUpPower");
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, amount2);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if (card.type != AbstractCard.CardType.ATTACK || AbstractDungeon.player.hasPower(StarPower.POWER_ID) && AbstractDungeon.player.getPower(StarPower.POWER_ID).amount < 10 || card.color == FGOCardColor.NOBLE_PHANTASM)
            return;
        
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }
}
