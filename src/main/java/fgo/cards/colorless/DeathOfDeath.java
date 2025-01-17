package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fgo.powers.DeathOfDeathPower;
import fgo.powers.GutsPower;

public class DeathOfDeath extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DeathOfDeath");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/DeathOfDeath.png";
    private static final int COST = 2;
    public static final String ID = "DeathOfDeath";
    public DeathOfDeath() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 25;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(15);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeathOfDeath();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, this.magicNumber, 1), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DeathOfDeathPower(p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        //this.addToBot(new ApplyPowerAction(p, p, new BriefStrengthPower(p, 1), 1));
    }
}
