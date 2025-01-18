package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FgoMaxHPAction extends AbstractGameAction {
    public FgoMaxHPAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.amount = amount;
    }

    public void update() {
        AbstractDungeon.player.increaseMaxHp(this.amount, true);
        this.isDone = true;
    }
}
