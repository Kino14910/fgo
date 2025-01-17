package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class TheGoldenFleece extends FGOCard {
    public static final String ID = makeID(TheGoldenFleece.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public TheGoldenFleece() {
        super(ID, INFO);
        setMagic(5, 3);
        setExhaust(true);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheGoldenFleece();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p, p, this.magicNumber));
        this.addToBot(new RemoveDebuffsAction(p));
    }
}
