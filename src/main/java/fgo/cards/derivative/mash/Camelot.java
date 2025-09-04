package fgo.cards.derivative.mash;

import static com.megacrit.cardcrawl.core.Settings.language;
import static fgo.FGOMod.imagePath;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;

public class Camelot extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Camelot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UpgradeName = cardStrings.EXTENDED_DESCRIPTION[1];
    private static final String UpgradeName2 = cardStrings.EXTENDED_DESCRIPTION[2];
    private static final String IMG2 = imagePath("cards/skill/MashPaladin.png");
    
    public Camelot() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setBlock(25);
        setMagic(6);
    }
    
    @Override
    public float getTitleFontSize() {
        if (language == Settings.GameLanguage.ZHS && upgraded) {
            return 24.0F;
        }

        return -1.0F;
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (timesUpgraded == 2) {
            loadCardImage(IMG2);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    @Override
    protected void upgradeName() {
        if(timesUpgraded == 1) {
            name = UpgradeName;
        }
        if (timesUpgraded == 2) {
            name = UpgradeName2;
            upgraded = true;
        }
        initializeTitle();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, magicNumber)));
    }
    
    @Override
    public boolean canUpgrade() {
        return false;
    }
}
