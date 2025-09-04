package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.IgnoreDefensePower;

public class ElementaryMyDear extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ElementaryMyDear.class.getSimpleName());

    public ElementaryMyDear() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(2, 2);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IgnoreDefensePower(p, magicNumber)));
    }
}
