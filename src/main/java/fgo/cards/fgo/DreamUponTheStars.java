package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.NPDamagePower;
public class DreamUponTheStars extends FGOCard {
    public static final String ID = makeID(DreamUponTheStars.class.getSimpleName());
    public DreamUponTheStars() {
        super(ID, 2, CardType.SKILL, CardTarget.SELF, CardRarity.BASIC);
        setBlock(8, 3);
        setNP(10);
        setMagic(20, 10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new NPDamagePower(magicNumber)));
    }



}


