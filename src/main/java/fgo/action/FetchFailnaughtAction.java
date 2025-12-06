package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.powers.CursePower;

public class FetchFailnaughtAction extends AbstractGameAction {

    public FetchFailnaughtAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        actionType = ActionType.DEBUFF;
    }

    @Override
    public void update() {
        if (target != null && target.hasPower(CursePower.POWER_ID)) {
            int curAmt = target.getPower(CursePower.POWER_ID).amount;
            addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new CursePower(target, curAmt)));
        }

        isDone = true;
    }
}
