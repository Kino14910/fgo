package fgo.cards.derivative.mash;

import static fgo.FGOMod.cardPath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.AntiPurgeDefensePower;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.ReducePercentDamagePower;

public class VeneratedShieldOfSnowflakes extends FGOCard {
    public static final String ID = makeID(VeneratedShieldOfSnowflakes.class.getSimpleName());

    public VeneratedShieldOfSnowflakes() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.SPECIAL, FGOCardColor.FGO_DERIVATIVE, cardPath("skill/MashPaladin"));
        setMagic(20);
        setNP(20, 10);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber)));
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 30)));
        addToBot(new ApplyPowerAction(p, p, new AntiPurgeDefensePower(p, 1)));
    }
}
