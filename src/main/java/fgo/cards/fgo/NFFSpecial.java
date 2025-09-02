package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.cards.tempCards.PoisonousDagger;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.utils.CardStats;

public class NFFSpecial extends FGOCard {
    public static final String ID = makeID(NFFSpecial.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public NFFSpecial() {
        super(ID, INFO);
        setMagic(2, 1);
        cardsToPreview = new PoisonousDagger();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new MakeTempCardInDiscardAction(cardsToPreview, 2));
    }
}
