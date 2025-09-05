package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.NightlessCharismaPower;
public class NightlessCharisma extends FGOCard {
    public static final String ID = makeID(NightlessCharisma.class.getSimpleName());
    public NightlessCharisma() {
        super(ID, 1, CardType.POWER, CardTarget.SELF, CardRarity.RARE);
        setMagic(1);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NightlessCharismaPower(p, magicNumber)));
    }
}


