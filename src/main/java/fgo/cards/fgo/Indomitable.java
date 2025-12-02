package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.HeraclesGutsPower;
import fgo.powers.IndomitablePower;

public class Indomitable extends FGOCard {
    public static final String ID = makeID(Indomitable.class.getSimpleName());
    public Indomitable() {
        super(ID, 1, CardType.POWER, CardTarget.SELF, CardRarity.RARE);
        setMagic(5, 5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HeraclesGutsPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new IndomitablePower(p, 2)));
    }
}


