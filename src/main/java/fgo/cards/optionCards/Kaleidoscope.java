package fgo.cards.optionCards;

import basemod.AutoAdd;
import fgo.action.FgoNpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

@AutoAdd.Ignore
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
