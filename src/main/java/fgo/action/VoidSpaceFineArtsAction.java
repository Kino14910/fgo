package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.powers.CursePower;

public class VoidSpaceFineArtsAction extends AbstractGameAction {
    private final int magicNumber;
    public VoidSpaceFineArtsAction(int amount) {
        this.magicNumber = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(CursePower.POWER_ID)) {
            int CurseAmt = 0;
            CurseAmt += (AbstractDungeon.player.getPower(CursePower.POWER_ID)).amount;
            this.addToBot(new FgoNpAction(this.magicNumber * CurseAmt));
        }

        this.isDone = true;
    }
}
