package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.MonteCristoTreasurePower;
import fgo.utils.CardStats;

public class MonteCristoTreasure extends FGOCard {
    public static final String ID = makeID(MonteCristoTreasure.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public MonteCristoTreasure() {
        super(ID, INFO);
        setMagic(1);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MonteCristoTreasurePower(p, magicNumber)));
    }
}
