package fgo.cards.derivative.mash;

import static com.megacrit.cardcrawl.core.Settings.language;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.ReducePercentDamagePower;

public class Camelot extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Camelot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    
    public Camelot() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setBlock(5, 7);
        setMagic(10, 20);
    }
    
    @Override
    public float getTitleFontSize() {
        if (language == Settings.GameLanguage.ZHS) {
            return 24.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, block)));
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber)));
    }
}
