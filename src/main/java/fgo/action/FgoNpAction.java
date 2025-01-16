package fgo.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import fgo.characters.master;

public class FgoNpAction extends AbstractGameAction {
    private final int amount;
    public boolean canText;
    public FgoNpAction(int amount) {
        this(amount, true);
    }

    public FgoNpAction(int amount, boolean canText) {
        this.amount = amount;
        this.canText = canText;
    }

    public void update() {
//        if (this.amount > 0) {
//            if (master.fgoNp + this.amount >= 300) {
//                master.fgoNp = 300;
//            } else {
//                master.fgoNp += this.amount;
//            }
//        } else {
//            if (master.fgoNp + this.amount <= 0) {
//                master.fgoNp = 0;
//            } else {
//                master.fgoNp += this.amount;
//            }
//        }

        master.fgoNp = master.fgoNp == 99
                ? 100
                : Math.min(Math.max(master.fgoNp + this.amount, 0), 300);

        if (this.canText) {
            String text = "NP" + (this.amount > 0 ? "+" : "") + this.amount + "%";
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(
            AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX,
            AbstractDungeon.player.hb.cY + AbstractDungeon.player.hb.height / 2.0F,
            text,
            Color.WHITE.cpy()));
}


//        if (master.fgoNp == 99) {
//            //如果你有99%的宝具值，立即获得1%宝具值。
//            ++master.fgoNp;
//        }

        if (AbstractDungeon.player instanceof master) {
            ((master)AbstractDungeon.player).TruthValueUpdatedEvent();
        }

        this.isDone = true;
    }
}
