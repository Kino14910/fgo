package fgo.cards.derivative.mash;

import static fgo.FGOMod.imagePath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.AntiPurgeDefensePower;
import fgo.utils.CardStats;

public class VeneratedShieldOfSnowflakes extends FGOCard {
    public static final String ID = makeID(VeneratedShieldOfSnowflakes.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = imagePath("cards/skill/MashPaladin.png");
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );

    public VeneratedShieldOfSnowflakes() {
        super(ID, INFO, IMG);
        setMagic(20);
        setNP(20, 10);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReducePercentDamagePower(magicNumber))
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 30)));
        addToBot(new ApplyPowerAction(p, p, new AntiPurgeDefensePower(p, 1)));
    }
}
