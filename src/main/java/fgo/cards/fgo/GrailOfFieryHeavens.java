package fgo.cards.fgo;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.EnergyRegenPower;
import fgo.util.CardStats;

public class GrailOfFieryHeavens extends FGOCard {
    public static final String ID = makeID(GrailOfFieryHeavens.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public GrailOfFieryHeavens() {
        super(ID, INFO);
        setMagic(10);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new GrailOfFieryHeavens();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, magicNumber), magicNumber));
    }
}
