package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.powers.GutsPower;
import fgo.powers.NPRatePower;
import fgo.powers.SpringOfFirePower;

public class SpringOfFire extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SpringOfFire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/SpringOfFire.png";
    private static final int COST = 3;
    public static final String ID = "SpringOfFire";
    public SpringOfFire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
        }
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
