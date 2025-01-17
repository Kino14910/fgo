package fgo.cards.colorless;

import fgo.action.TheOneWhoSawItAllAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class TheOneWhoSawItAll extends FGOCard {
    public static final String ID = makeID(TheOneWhoSawItAll.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );
    public TheOneWhoSawItAll() {
        super(ID, INFO);
        setMagic(4, 2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheOneWhoSawItAll();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TheOneWhoSawItAllAction(1, CardType.ATTACK, this.magicNumber));
    }
}
