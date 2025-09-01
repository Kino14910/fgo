package fgo.cards.fgo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.Master;
import fgo.patches.Enum.FGOCardColor;
import fgo.utils.CardStats;

public class Borrowingfrom extends FGOCard {
    public static final String ID = makeID(Borrowingfrom.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );
    public Borrowingfrom() {
        super(ID, INFO);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FgoNpAction(Master.fgoNp));
    }
}
