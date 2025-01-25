package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.PowerUpBoostPower;
import fgo.powers.deprecated.EndOfADreamPower;
import fgo.util.CardStats;

public class EndOfADream extends FGOCard {
    public static final String ID = makeID(EndOfADream.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );
    public EndOfADream() {
        super(ID, INFO);
        setMagic(1);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PowerUpBoostPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new EndOfADreamPower((p))));
    }
}
