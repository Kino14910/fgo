package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.BlessingOfKurPower;
public class BlessingOfKur extends FGOCard {
    public static final String ID = makeID(BlessingOfKur.class.getSimpleName());
    public BlessingOfKur() {
        super(ID, 1, CardType.SKILL, CardTarget.NONE, CardRarity.RARE);
        setMagic(15, 5);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BlessingOfKurPower((p))));
    }
}


