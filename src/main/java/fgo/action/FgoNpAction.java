package fgo.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;

import fgo.characters.Master;

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
        int oldNp = Master.fgoNp;
        // 0 <= fgoNP <=300
        Master.fgoNp = Math.min(Math.max(Master.fgoNp + this.amount, 0), 300);
        // 保留了一部分fgo特性，让你知道自己在玩fgo
        if(Master.fgoNp == 99 && oldNp < 99){
            Master.fgoNp = 100;
        }

        if (this.canText) {
            String text = "NP" + (this.amount > 0 ? "+" : "") + this.amount + "%";
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(
            AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX,
            AbstractDungeon.player.hb.cY + AbstractDungeon.player.hb.height / 2.0F,
            text,
            Color.WHITE.cpy()));
}

        if (AbstractDungeon.player instanceof Master) {
            ((Master)AbstractDungeon.player).TruthValueUpdatedEvent();
        }

        this.isDone = true;
    }
}
