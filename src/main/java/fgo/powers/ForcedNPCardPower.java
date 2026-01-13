package fgo.powers;

import static fgo.FGOMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class ForcedNPCardPower extends BasePower implements NonStackablePower{
    public static final String POWER_ID = makeID(ForcedNPCardPower.class.getSimpleName());
    public AbstractCard card;
 
    public ForcedNPCardPower(AbstractCreature owner, AbstractCard copyMe, boolean isUpgraded) {
        super(POWER_ID, PowerType.BUFF, false, owner, owner, -1, "PutOnFakeFacePower", false);
        card = copyMe.makeStatEquivalentCopy();
        card.resetAttributes();
        if (isUpgraded) {
            card.upgrade();
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], FontHelper.colorString(card.name, "y"));
    }
}
