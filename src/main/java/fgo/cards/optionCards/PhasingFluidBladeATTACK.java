package fgo.cards.optionCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class PhasingFluidBladeATTACK extends FGOCard {
    public static final String ID = makeID(PhasingFluidBladeATTACK.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.COMMON,
            CardTarget.SELF,
            -2
    );
    public PhasingFluidBladeATTACK() {
        super(ID, INFO);
        setMagic(2, 1);
    }

    


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void onChoseThisOption() {
        addToBot(new DrawPileToHandAction(magicNumber, CardType.ATTACK));
    }
}
