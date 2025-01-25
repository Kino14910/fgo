package fgo.cards.optionCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class ReleaseNoblePhantasm extends FGOCard {
    public static final String ID = makeID(ReleaseNoblePhantasm.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public static final String IMG = "fgo/images/cards/power/CommandSpellGuts.png";
    public ReleaseNoblePhantasm() {
        super(ID, INFO, IMG);
        setNP(100);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        this.addToBot(new FgoNpAction(np));
    }
}
