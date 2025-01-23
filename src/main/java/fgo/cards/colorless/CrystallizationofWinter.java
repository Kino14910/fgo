package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.ComeOnPower;
import fgo.powers.GutsPower;
import fgo.util.CardStats;

public class CrystallizationofWinter extends FGOCard {
    public static final String ID = makeID(CrystallizationofWinter.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );
    public static final String IMG = "fgo/images/cards/power/ConjureIce.png";
    public CrystallizationofWinter() {
        super(ID, INFO, IMG);
        setMagic(4, 1);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, 10, 1), 10));
        //this.addToBot(new ApplyPowerAction(p, p, new TheOneWhoRevealsTruthPower(p)));
        this.addToBot(new ApplyPowerAction(p, p, new ComeOnPower(p, this.magicNumber), this.magicNumber));
    }
}
