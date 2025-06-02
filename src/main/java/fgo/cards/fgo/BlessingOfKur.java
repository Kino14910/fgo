package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.BlessingOfKurPower;
import fgo.util.CardStats;

public class BlessingOfKur extends FGOCard {
    public static final String ID = makeID(BlessingOfKur.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1
    );
    public BlessingOfKur() {
        super(ID, INFO);
        setMagic(15, 5);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BlessingOfKurPower((p))));
    }
}
