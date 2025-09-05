package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
public class Coaching extends FGOCard {
    public static final String ID = makeID(Coaching.class.getSimpleName());
    public Coaching() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setMagic(2);
        setCostUpgrade(0);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
    }
}

