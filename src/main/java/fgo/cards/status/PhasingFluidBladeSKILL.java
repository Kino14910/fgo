package fgo.cards.status;

import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class PhasingFluidBladeSKILL extends FGOCard {
    public static final String ID = makeID(PhasingFluidBladeSKILL.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.COMMON,
            CardTarget.SELF,
            -2
    );
    public PhasingFluidBladeSKILL() {
        super(ID, INFO);
        setMagic(2, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PhasingFluidBladeSKILL();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void onChoseThisOption() {
        addToBot(new DrawPileToHandAction(magicNumber, CardType.SKILL));
    }
}
