package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.DeathOfDeathPower;
import fgo.powers.GutsPower;
import fgo.utils.CardStats;

public class DeathOfDeath extends FGOCard {
    public static final String ID = makeID(DeathOfDeath.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public DeathOfDeath() {
        super(ID, INFO);
        setMagic(15, 10);
        setExhaust();
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, this.magicNumber, 1)));
        this.addToBot(new ApplyPowerAction(p, p, new DeathOfDeathPower(p, 3)));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
        //this.addToBot(new ApplyPowerAction(p, p, new BriefStrengthPower(p, 1), 1));
    }
}
