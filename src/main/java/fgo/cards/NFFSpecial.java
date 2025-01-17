package fgo.cards;

import fgo.cards.colorless.PoisonousDagger;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.util.CardStats;
import fgo.patches.Enum.FGOCardColor;

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
        setMagic(3, 1);
        this.cardsToPreview = new PoisonousDagger();
    }

    @Override
    public AbstractCard makeCopy() {
        return new NFFSpecial();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview, 2));
    }
}
