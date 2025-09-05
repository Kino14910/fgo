package fgo.cards.fgo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.CurtainoftheNightAction;
import fgo.cards.FGOCard;
public class CurtainoftheNight extends FGOCard {
    public static final String ID = makeID(CurtainoftheNight.class.getSimpleName());
    public CurtainoftheNight() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.COMMON);
        setMagic(1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CurtainoftheNightAction(magicNumber, true));
    }
}


