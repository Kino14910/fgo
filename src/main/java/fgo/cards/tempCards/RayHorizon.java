package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.cards.noblecards.HollowHeartAlbion;
import fgo.powers.InvincibilityTurnPower;
import fgo.powers.NoblePhantasmCardPower;

public class RayHorizon extends FGOCard {
    public static final String ID = makeID(RayHorizon.class.getSimpleName());

    public RayHorizon() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.SPECIAL, CardColor.COLORLESS);
        setNP(50, 50);
        cardsToPreview = new HollowHeartAlbion();
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new NoblePhantasmCardPower(p, cardsToPreview)));
        addToBot(new ApplyPowerAction(p, p, new InvincibilityTurnPower(p, magicNumber)));
    }
}
