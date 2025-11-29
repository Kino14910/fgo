package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.NPDamagePower;
public class TeachingsoftheEternal extends FGOCard {
    public static final String ID = makeID(TeachingsoftheEternal.class.getSimpleName());
    public TeachingsoftheEternal() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.COMMON);
        setNP(10, 5);
        setMagic(10, 5);
    }
    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new NPDamagePower(magicNumber)));
    }
}


