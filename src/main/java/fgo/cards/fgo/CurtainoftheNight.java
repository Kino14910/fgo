package fgo.cards.fgo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.MeditateFateAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class CurtainoftheNight extends FGOCard {
    public static final String ID = makeID(CurtainoftheNight.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public CurtainoftheNight() {
        super(ID, INFO);
        setMagic(1, 1);
    }

    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MeditateFateAction(magicNumber, true));
    }
}
