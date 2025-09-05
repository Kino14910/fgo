package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.StarPower;
public class StarHunter extends FGOCard {
    public static final String ID = makeID(StarHunter.class.getSimpleName());
    public StarHunter() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.RARE);
        setMagic(3, 1);
        setStar(10);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
    }
}


