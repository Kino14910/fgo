package fgo.action.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;

public class GodsExecutionAction extends AbstractGameAction
{
    private DamageInfo info;
    
    public GodsExecutionAction(final AbstractCreature target, final DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }
    
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                addToTop(new DrawCardAction(AbstractDungeon.player, 1));
                addToTop(new GainEnergyAction(1));
                break;
            }
        }
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}
