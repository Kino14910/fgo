package fgo.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.TameshiMonoAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class TameshiMono extends FGOCard {
    public static final String ID = makeID(TameshiMono.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public TameshiMono() {
        super(ID, INFO);
        setBlock(11, 3);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TameshiMonoAction());
        addToBot(new GainBlockAction(p, p, block));
    }
}
