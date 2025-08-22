package fgo.cards.colorless.mash;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.ReducePercentDamagePower;
import fgo.util.CardStats;

import static com.megacrit.cardcrawl.core.Settings.language;

public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );
    public WallOfSnowflakes() {
        super(ID, INFO);
        setBlock(15, 5);
        setMagic(20);
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
        timesUpgraded++;
        upgraded = true;
        name = "荣光坚毅的雪花之壁";
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber), magicNumber));
        }
    }
}
