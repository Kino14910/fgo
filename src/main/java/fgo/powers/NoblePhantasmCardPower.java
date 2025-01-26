package fgo.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class NoblePhantasmCardPower extends BasePower {
    public static final String POWER_ID = makeID(NoblePhantasmCardPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractCard card;
 
    public NoblePhantasmCardPower(AbstractCreature owner, AbstractCard copyMe) {
        super(POWER_ID, TYPE, TURN_BASED, owner, "PutOnFakeFacePower");
        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1];
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new MakeTempCardInHandAction(this.card.makeStatEquivalentCopy(), 1));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    
}
