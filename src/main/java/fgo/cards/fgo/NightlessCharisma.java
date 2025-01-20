package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.NightlessCharismaPower;
import fgo.util.CardStats;

public class NightlessCharisma extends FGOCard {
    public static final String ID = makeID(NightlessCharisma.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    public NightlessCharisma() {
        super(ID, INFO);
        setMagic(1);
        setCostUpgrade(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new NightlessCharisma();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NightlessCharismaPower(p, magicNumber)));
    }
}
