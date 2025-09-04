package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.SunlightPower;

public class ExcaliburGalatine extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ExcaliburGalatine.class.getSimpleName());

    public ExcaliburGalatine() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(3, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SunlightPower(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber)));
        //addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50)));
    }
}
