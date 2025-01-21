package fgo.cards.fgo.Ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.MyFairSoldierPower;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class QueensCovenant extends FGOCard {
    public static final String ID = makeID(QueensCovenant.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public QueensCovenant() {
        super(ID, INFO);
        setMagic(3, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new QueensCovenant();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MyFairSoldierPower(p, magicNumber)));
    }
}
