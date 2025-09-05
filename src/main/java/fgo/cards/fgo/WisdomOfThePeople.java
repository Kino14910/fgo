package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
public class WisdomOfThePeople extends FGOCard {
    public static final String ID = makeID(WisdomOfThePeople.class.getSimpleName());
    public WisdomOfThePeople() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.RARE);
        setMagic(10);
        setNP(30);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        if (upgraded) {
            addToBot(new FgoNpAction(np));
        }
        addToTop(new RemoveDebuffsAction(p));
    }
}


