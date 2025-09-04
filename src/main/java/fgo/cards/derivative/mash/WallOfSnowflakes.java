package fgo.cards.derivative.mash;

import static fgo.FGOMod.imagePath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.ReducePercentDamagePower;
import fgo.utils.CardStats;

public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String upgradeName = cardStrings.EXTENDED_DESCRIPTION[1];
    private static final String upgradeName2 = cardStrings.EXTENDED_DESCRIPTION[2];
    private static final String IMG2 = imagePath("cards/skill/MashPaladin.png");
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );
    public WallOfSnowflakes() {
        super(ID, INFO);
        setBlock(10);
        setMagic(20);
        setNP(20);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public float getTitleFontSize() {
        return 24.0F;
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (timesUpgraded == 2) {
            loadCardImage(IMG2);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        };
        upgraded = false;
    }

    @Override
    protected void upgradeName() {
        ++this.timesUpgraded;
        if(timesUpgraded == 1) {
            name = upgradeName;
        }
        if (timesUpgraded == 2) {
            name = upgradeName2;
            upgraded = true;
        }
        initializeTitle();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (timesUpgraded >= 0) {
            addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber), magicNumber));
        }
        if (timesUpgraded == 1) {
            addToBot(new GainBlockAction(p, p, block));
        }
        if (timesUpgraded == 2) {
            addToBot(new FgoNpAction(np));
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
            addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 30)));
        }
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }
}
