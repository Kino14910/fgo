package fgo.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class Kaleidoscope extends FGOCard {
    public static final String ID = makeID(Kaleidoscope.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2
    );
    public Kaleidoscope() {
        super(ID, INFO);
        setNP(80, 20);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new FgoNpAction(np));
    }
}
