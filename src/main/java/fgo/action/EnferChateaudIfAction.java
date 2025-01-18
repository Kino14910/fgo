package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.WingBoots;

public class EnferChateaudIfAction extends AbstractGameAction {
    @Override
    public void update() {
        if (!AbstractDungeon.player.hasRelic("WingedGreaves")) {
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new WingBoots());
        } else if (AbstractDungeon.player.hasRelic("WingedGreaves") && AbstractDungeon.player.getRelic("WingedGreaves").counter == 0) {
            AbstractDungeon.player.loseRelic("WingedGreaves");
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new WingBoots());
        }

        this.isDone = true;
    }
}
