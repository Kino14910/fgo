package fgo.cards.colorless;

import fgo.action.FgoNpAction;
import basemod.abstracts.CustomCard;
import fgo.cards.noblecards.HollowHeartAlbion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.powers.InvincibilityTurnPower;
import fgo.powers.NoblePhantasmCardPower;

public class RayHorizon extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RayHorizon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/RayHorizon.png";
    private static final int COST = 0;

    public static final String ID = "RayHorizon";
    public RayHorizon() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 50;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new HollowHeartAlbion();
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(50);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RayHorizon();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new NoblePhantasmCardPower(p, this.cardsToPreview)));
        this.addToBot(new ApplyPowerAction(p, p, new InvincibilityTurnPower(p, 1), 1));
    }
}
