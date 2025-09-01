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
        this.actionType = ActionType.DEBUFF;
    }

    public void update() {
        if (this.target != null && this.target.hasPower(CursePower.POWER_ID)) {
            int curAmt = this.target.getPower(CursePower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new CursePower(this.target, curAmt), curAmt));
        }

        this.isDone = true;
    }
}
