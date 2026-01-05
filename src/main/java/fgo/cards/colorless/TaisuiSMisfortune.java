package fgo.cards.colorless;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;

import fgo.cards.FGOCard;
import fgo.powers.TaisuiSPower;
public class TaisuiSMisfortune extends FGOCard {
    public static final String ID = makeID(TaisuiSMisfortune.class.getSimpleName());
    public TaisuiSMisfortune() {
        super(ID, 0, CardType.SKILL, CardTarget.ALL_ENEMY, CardRarity.UNCOMMON, CardColor.COLORLESS);
        setMagic(2, 1);
        setExhaust();
        setInnate();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }
        addToBot(new GainEnergyAction(1));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber,
                monster -> new TaisuiSPower(monster, magicNumber))
        );
    }
}


