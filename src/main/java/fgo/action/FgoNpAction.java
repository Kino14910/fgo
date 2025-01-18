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
        int oldNp = master.fgoNp;
        // 0 <= fgoNP <=300
        master.fgoNp = Math.min(Math.max(master.fgoNp + this.amount, 0), 300);
        // 保留了一部分fgo特性，让你知道自己在玩fgo
        if(master.fgoNp == 99 && oldNp < 99){
            master.fgoNp = 100;
        }

        if (this.canText) {
            String text = "NP" + (this.amount > 0 ? "+" : "") + this.amount + "%";
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(
            AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX,
            AbstractDungeon.player.hb.cY + AbstractDungeon.player.hb.height / 2.0F,
            text,
            Color.WHITE.cpy()));
}

        if (AbstractDungeon.player instanceof master) {
            ((master)AbstractDungeon.player).TruthValueUpdatedEvent();
        }

        this.isDone = true;
    }
}
