package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
            this.addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }
        this.addToBot(new GainEnergyAction(1));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new TaisuiSPower(mo, this.magicNumber), this.magicNumber));
        }
    }
}


