package fgo.cards.fgo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.GoldenGrailAction;
import fgo.action.SevenBeastCrownsAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class GoldenGrail extends FGOCard {
    public static final String ID = makeID(GoldenGrail.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            -1
    );
    public GoldenGrail() {
        super(ID, INFO);
        setMagic(3, 1);
        setExhaust();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GoldenGrail();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GoldenGrailAction(p, freeToPlayOnce, energyOnUse, magicNumber));
        addToBot(new SevenBeastCrownsAction(p, freeToPlayOnce, energyOnUse));
    }
}
