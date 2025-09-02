package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.AtTheWellPower;
import fgo.utils.CardStats;

public class AtTheWell extends FGOCard {
    public static final String ID = makeID(AtTheWell.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    public AtTheWell() {
        super(ID, INFO);
        setMagic(10, 10);
        setExhaust();
        tags.add(CardTags.HEALING);
    }

    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveAllPowersAction(p, true));
        addToBot(new ApplyPowerAction(p, p, new AtTheWellPower(p, magicNumber)));
    }
}
