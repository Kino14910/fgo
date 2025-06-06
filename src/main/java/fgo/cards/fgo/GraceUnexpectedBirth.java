package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.SealNPPower;
import fgo.util.CardStats;

import static com.megacrit.cardcrawl.core.Settings.language;

public class GraceUnexpectedBirth extends FGOCard {
    public static final String ID = makeID(GraceUnexpectedBirth.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public GraceUnexpectedBirth() {
        super(ID, INFO);
        setNP(30, 20);
        setExhaust();
    }

    @Override
    public float getTitleFontSize() {
        if (language != Settings.GameLanguage.ZHS) {
            return 16.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new SealNPPower(p, 1)));
        //addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1), 1));
    }
}
