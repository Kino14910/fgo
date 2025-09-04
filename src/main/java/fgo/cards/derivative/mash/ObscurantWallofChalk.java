package fgo.cards.derivative.mash;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.ReducePercentDamagePower;
import fgo.utils.CardStats;

public class ObscurantWallofChalk extends FGOCard {
    public static final String ID = makeID(ObscurantWallofChalk.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            2
    );

    public ObscurantWallofChalk() {
        super(ID, INFO);
        setNP(20, 10);
    }
    
    @Override
    public float getTitleFontSize() {
        return 24.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded){
            addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1)));
            addToBot(new FgoNpAction(np));
            return;
        }
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, 20)));
        addToBot(new ApplyPowerAction(p, p, new IntangiblePower(p, 1)));
        addToBot(new FgoNpAction(np));
    }
}
