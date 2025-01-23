package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.NPRatePower;
import fgo.powers.SchwarzwaldFalkePower;
import fgo.util.CardStats;

public class SchwarzwaldFalke extends FGOCard {
    public static final String ID = makeID(SchwarzwaldFalke.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            3
    );
    public SchwarzwaldFalke() {
        super(ID, INFO);
        setMagic(2);
        setExhaust();
        setCostUpgrade(2);
    }

    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }

        addToBot(new ApplyPowerAction(p, p, new SchwarzwaldFalkePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, magicNumber)));
    }
}
