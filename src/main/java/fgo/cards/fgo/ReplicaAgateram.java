package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.cards.FGOCard;
public class ReplicaAgateram extends FGOCard {
    public static final String ID = makeID(ReplicaAgateram.class.getSimpleName());
    public ReplicaAgateram() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON, CardColor.COLORLESS);
        setBlock(6, 4);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new ReducePowerAction(p, p, power, 1));
                this.addToBot(new GainBlockAction(p, p, block));
            }
        }
    }
}


