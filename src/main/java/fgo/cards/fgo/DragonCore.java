package fgo.cards.fgo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.DragonCoreAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class DragonCore extends FGOCard {
    public static final String ID = makeID(DragonCore.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public DragonCore() {
        super(ID, INFO);
        setMagic(2);
        setExhaust();
        setCostUpgrade(1);
    }

    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DragonCoreAction());
    }
}
