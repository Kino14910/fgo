package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.LoseCritDamagePower;
import fgo.util.CardStats;

public class HeroCreation extends FGOCard {
    public static final String ID = makeID(HeroCreation.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public HeroCreation() {
        super(ID, INFO);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 100)));
        addToBot(new ApplyPowerAction(p, p, new LoseCritDamagePower(p, 100)));
    }
}
