package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class SwordOfSelection extends FGOCard {
    public static final String ID = makeID(SwordOfSelection.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public SwordOfSelection() {
        super(ID, INFO);
        setMagic(3, 1);
        setCasterBackground();
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        setNP(p.hand.size());
        addToBot(new FgoNpAction(np, true));
        //addToBot(new ApplyPowerAction(p, p, new ArtsPerformancePower(p, 2), 2));
    }

    @Override
    public void applyPowers() {
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        super.applyPowers();
        initializeDescription();
    }
}
