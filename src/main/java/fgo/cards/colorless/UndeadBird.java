package fgo.cards.colorless;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.RandomCardWithTagAction;
import fgo.cards.FGOCard;
import fgo.utils.CardStats;

public class UndeadBird extends FGOCard {
    public static final String ID = makeID(UndeadBird.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public UndeadBird() {
        super(ID, INFO);
        setMagic(1, 1);
        setExhaust();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new RandomCardWithTagAction(false, CardTags.HEALING, false, false));
        }
    }
}
