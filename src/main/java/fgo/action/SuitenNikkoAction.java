package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SuitenNikkoAction
extends AbstractGameAction {
    public SuitenNikkoAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            c.setCostForTurn(c.costForTurn - this.amount);
        }
        this.isDone = true;
    }
}

