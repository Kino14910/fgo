package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class WaitFgoAction extends AbstractGameAction {
    public WaitFgoAction(float setDur) {
        this.setValues(null, null, 0);
        this.duration = setDur;

        this.actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        this.tickDuration();
    }
}
