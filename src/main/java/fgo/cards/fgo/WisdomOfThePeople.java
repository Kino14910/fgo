package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.utils.CardStats;

public class WisdomOfThePeople extends FGOCard {
    public static final String ID = makeID(WisdomOfThePeople.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    public WisdomOfThePeople() {
        super(ID, INFO);
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
