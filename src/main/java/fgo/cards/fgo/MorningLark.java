package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.MorningLarkPower;
import fgo.powers.StarGainPower;
import fgo.util.CardStats;

public class MorningLark extends FGOCard {
    public static final String ID = makeID(MorningLark.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public MorningLark() {
        super(ID, INFO);
        setNP(30, 20);
        setStar(10, 10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, star)));
        addToBot(new ApplyPowerAction(p, p, new MorningLarkPower(p, 1), 1));
//        addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1), 1));
    }
}
