package fgo.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;

import static fgo.FGOMod.makeID;

public class UnlimitedPower extends BasePower {
    public static final String POWER_ID = makeID(UnlimitedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UnlimitedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "PutOnFakeFacePower");
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for(int i = 0; i < this.amount; ++i) {
                AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
                card.setCostForTurn(0);
                CardModifierManager.addModifier(card, new ExhaustMod());
                this.addToBot(new MakeTempCardInHandAction(card, 1, true));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
