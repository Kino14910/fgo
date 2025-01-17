package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.WallOfSnowflakesPower;
import fgo.util.CardStats;

import static com.megacrit.cardcrawl.core.Settings.language;


public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1
    );
    public WallOfSnowflakes() {
        super(ID, INFO);
        setBlock(20);
        setMagic(15, 5);
        setCostUpgrade(2);
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
        ++timesUpgraded;
        upgraded = true;
        name = "荣光坚毅的雪花之壁";
    }

    @Override
    public AbstractCard makeCopy() {
        return new WallOfSnowflakes();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
        this.addToBot(new ApplyPowerAction(p, p, new WallOfSnowflakesPower(p, this.magicNumber), this.magicNumber));
    }
}
