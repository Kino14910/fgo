package fgo.cards.fgo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.JewelOfFourteenAction;
import fgo.cards.FGOCard;
import fgo.cards.tempCards.FlamesofApplause;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class JewelOfFourteen extends FGOCard {
    public static final String ID = makeID(JewelOfFourteen.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public JewelOfFourteen() {
        super(ID, INFO);
        cardsToPreview = new FlamesofApplause();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JewelOfFourteenAction(upgraded, 10));
    }
}
