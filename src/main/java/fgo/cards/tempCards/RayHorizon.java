package fgo.cards.tempCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.cards.noblecards.HollowHeartAlbion;
import fgo.powers.InvincibilityTurnPower;
import fgo.powers.NoblePhantasmCardPower;
import fgo.util.CardStats;

@AutoAdd.Ignore
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
        setNP(50, 50);
        this.cardsToPreview = new HollowHeartAlbion();
        setExhaust();
    }

    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new NoblePhantasmCardPower(p, this.cardsToPreview)));
        this.addToBot(new ApplyPowerAction(p, p, new InvincibilityTurnPower(p, np), np));
    }
}
