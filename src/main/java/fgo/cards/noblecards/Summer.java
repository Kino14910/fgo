package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.WatersidePower;

public class Summer extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Summer.class.getSimpleName());

    public Summer() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setNP(0, 20);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WatersidePower(p)));
        addToBot(new FgoNpAction(np));
    }
}
