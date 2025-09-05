package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.StarPower;
public class TameshiMono extends FGOCard {
    public static final String ID = makeID(TameshiMono.class.getSimpleName());
    public TameshiMono() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setMagic(10, 5);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, m, new StarPower(p, magicNumber)));
        addToBot(new ExhaustAction(1, false));
    }
}


