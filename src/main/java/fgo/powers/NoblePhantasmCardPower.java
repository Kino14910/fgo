package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class NoblePhantasmCardPower extends BasePower {
    public static final String POWER_ID = makeID(NoblePhantasmCardPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    public static AbstractCard card;
 
    public NoblePhantasmCardPower(AbstractCreature owner, AbstractCard copyMe) {
        super(POWER_ID, TYPE, false, owner, owner, -1, "PutOnFakeFacePower", false);
        card = copyMe.makeStatEquivalentCopy();
        card.resetAttributes();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], FontHelper.colorString(card.name, "y"));
    }

    @Override
    public void onSpecificTrigger() {
        addToBot(new MakeTempCardInHandAction(card.makeStatEquivalentCopy(), 1));
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }


}
