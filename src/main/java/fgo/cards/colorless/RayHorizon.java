package fgo.cards.colorless;

import fgo.action.FgoNpAction;
import basemod.abstracts.CustomCard;
import fgo.cards.FGOCard;
import fgo.cards.noblecards.HollowHeartAlbion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.powers.InvincibilityTurnPower;
import fgo.powers.NoblePhantasmCardPower;
import fgo.util.CardStats;

public class RayHorizon extends FGOCard {
    public static final String ID = makeID(RayHorizon.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );
    public RayHorizon() {
        super(ID, INFO);
        setMagic(50, 50);
        this.cardsToPreview = new HollowHeartAlbion();
        setExhaust(true);
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
