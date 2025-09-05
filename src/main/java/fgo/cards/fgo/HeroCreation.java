package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.LoseCritDamagePower;
public class HeroCreation extends FGOCard {
    public static final String ID = makeID(HeroCreation.class.getSimpleName());
    public HeroCreation() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 100)));
        addToBot(new ApplyPowerAction(p, p, new LoseCritDamagePower(p, 100)));
    }
}


