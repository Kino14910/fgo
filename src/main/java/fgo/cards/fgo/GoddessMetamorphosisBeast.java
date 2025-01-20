package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.GoddessMetamorphosisBeastPower;
import fgo.util.CardStats;

public class GoddessMetamorphosisBeast extends FGOCard {
    public static final String ID = makeID(GoddessMetamorphosisBeast.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public GoddessMetamorphosisBeast() {
        super(ID, INFO);
        setMagic(1);
        setEthereal(true, false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new GoddessMetamorphosisBeast();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GoddessMetamorphosisBeastPower(p, magicNumber)));
    }
}
