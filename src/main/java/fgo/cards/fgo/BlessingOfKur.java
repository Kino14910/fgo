package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.BlessingOfKurAction;
import fgo.cards.FGOCard;
import fgo.cards.noblecards.KurKigalIrkalla;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.BlessingOfKurPower;
import fgo.util.CardStats;

public class BlessingOfKur extends FGOCard {
    public static final String ID = makeID(BlessingOfKur.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            -1
    );
    public BlessingOfKur() {
        super(ID, INFO);
        setBlock(10,3);
        cardsToPreview = new KurKigalIrkalla();
        setExhaust();
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlessingOfKur();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BlessingOfKurAction(p, block, freeToPlayOnce, energyOnUse));
        //addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new BlessingOfKurPower(p)));
    }
}
