package fgo.cards.deprecated;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CurseHarmonyPower;
import fgo.util.CardStats;

public class CurseHarmony extends FGOCard {
    public static final String ID = makeID(CurseHarmony.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public CurseHarmony() {
        super(ID, INFO);
        setMagic(3, 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CurseHarmonyPower(p, magicNumber)));
    }

}
