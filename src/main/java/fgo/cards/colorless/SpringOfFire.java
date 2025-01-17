package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.GutsPower;
import fgo.powers.NPRatePower;
import fgo.powers.SpringOfFirePower;
import fgo.util.CardStats;

public class SpringOfFire extends FGOCard {
    public static final String ID = makeID(SpringOfFire.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public SpringOfFire() {
        super(ID, INFO);
        setMagic(3, 3);
        setEthereal(true);
        setExhaust(true);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpringOfFire();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(GutsPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, this.magicNumber, 3), this.magicNumber));
        }
        if (!p.hasPower(SpringOfFirePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new SpringOfFirePower(p, 20), 20));
        }
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, this.magicNumber), this.magicNumber));
    }
}
