package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import fgo.cards.FGOCard;
public class Revelation extends FGOCard {
    public static final String ID = makeID(Revelation.class.getSimpleName());
    public Revelation() {
        super(ID, 0, CardType.SKILL, CardTarget.ENEMY, CardRarity.UNCOMMON);
        setMagic(1, 1);
        setExhaust();
    }

    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
    }
}


