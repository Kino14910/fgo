package fgo.cards.fgo.Ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.AtTheWellPower;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class AtTheWell extends FGOCard {
    public static final String ID = makeID(AtTheWell.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );
    public AtTheWell() {
        super(ID, INFO);
        setMagic(15, 10);
        setExhaust();
        tags.add(CardTags.HEALING);
    }

    @Override
    public AbstractCard makeCopy() {
        return new AtTheWell();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*addToBot(new RemoveAllPowersAction(p, true));
        if (upgraded) {
            addToBot(new HealAction(p, p, magicNumber));
        }*/
        addToBot(new ApplyPowerAction(p, p, new AtTheWellPower(p, magicNumber)));
    }
}
