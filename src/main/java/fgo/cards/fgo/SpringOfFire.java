package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.GutsPower;
import fgo.powers.SpringOfFirePower;
import fgo.utils.CardStats;

public class SpringOfFire extends FGOCard {
    public static final String ID = makeID(SpringOfFire.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public SpringOfFire() {
        super(ID, INFO);
        setMagic(3, 3);
        setEthereal(true, false);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GutsPower(p, magicNumber, 3)));
        addToBot(new ApplyPowerAction(p, p, new SpringOfFirePower(p, magicNumber)));
    }
}
