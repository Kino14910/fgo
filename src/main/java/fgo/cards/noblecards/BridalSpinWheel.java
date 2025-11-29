package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.StarPower;

public class BridalSpinWheel extends AbsNoblePhantasmCard {
    public static final String ID = makeID(BridalSpinWheel.class.getSimpleName());

    public BridalSpinWheel() {
        super(ID,CardType.SKILL, CardTarget.SELF, 1);
        setMagic(3, 1);
        setStar(10, 10);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
    }
}
