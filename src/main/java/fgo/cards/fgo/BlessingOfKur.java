package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.cards.noblecards.KurKigalIrkalla;
import fgo.powers.ArtsPerformancePower;
import fgo.powers.BlessingOfKurPower;
public class BlessingOfKur extends FGOCard {
    public static final String ID = makeID(BlessingOfKur.class.getSimpleName());
    public BlessingOfKur() {
        super(ID, 1, CardType.POWER, CardTarget.SELF, CardRarity.RARE);
        setMagic(10, 5);
        setExhaust();
        cardsToPreview = new KurKigalIrkalla();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtsPerformancePower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new BlessingOfKurPower(p, magicNumber, 2)));
    }
}


