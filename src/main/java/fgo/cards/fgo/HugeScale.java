package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.cards.tempCards.InfantileRegression;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.HugeScalePower;
import fgo.util.CardStats;

public class HugeScale extends FGOCard {
    public static final String ID = makeID(HugeScale.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public HugeScale() {
        super(ID, INFO);
        setMagic(5, 1);
        cardsToPreview = new InfantileRegression();
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HugeScalePower(p, magicNumber)));
        addToTop(new MakeTempCardInHandAction(cardsToPreview, 1));
        //addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
    }
}
