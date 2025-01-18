package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class YinYangFish extends FGOCard {
    public static final String ID = makeID(YinYangFish.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public YinYangFish() {
        super(ID, INFO);
        setNP(10);
        setMagic(6);
        tags.add(CardTags.HEALING);
        setExhaust();
    }

    @Override
    public AbstractCard makeCopy() {
        return new YinYangFish();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot(new FgoNpAction(30));
        }
        addToBot(new FgoNpAction(-np));
        addToTop(new HealAction(p, p, magicNumber));
    }
}
