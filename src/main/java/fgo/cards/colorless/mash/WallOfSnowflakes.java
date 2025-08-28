package fgo.cards.colorless.mash;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.ReducePercentDamagePower;
import fgo.util.CardStats;

import static com.megacrit.cardcrawl.core.Settings.language;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;

public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
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
        if (language == Settings.GameLanguage.ZHS) {
            return 24.0F;
        }

        return -1.0F;
    }

    @Override
    protected void upgradeName() {
        if(timesUpgraded == 1) {
            initializeDescription();
            switch (language) {
                case ZHS:
                    name = "荣光坚毅的雪花之壁";
                    break;
                case ZHT:
                    name = "榮耀堅固的雪花之壁";
                    break;
                case JPN:
                    name = "誉れ堅き雪花の壁";
                    break;
                default:
                    break;
            }
        }
        if (timesUpgraded == 2) {
            cardStrings.UPGRADE_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
            switch (language) {
                case ZHS:
                    name = "荣光远扬的雪花之盾";
                    break;
                case ZHT:
                    name = "榮耀遠揚的雪花之盾";
                    break;
                case JPN:
                    name = "誉れ高き雪花の盾";
                    break;
                default:
                    break;
            }
        }
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
