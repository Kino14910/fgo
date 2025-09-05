package fgo.cards.fgo;

import static com.megacrit.cardcrawl.core.Settings.language;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.SealNPPower;
public class GraceUnexpectedBirth extends FGOCard {
    public static final String ID = makeID(GraceUnexpectedBirth.class.getSimpleName());
    public GraceUnexpectedBirth() {
        super(ID, 2, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setNP(30, 20);
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


