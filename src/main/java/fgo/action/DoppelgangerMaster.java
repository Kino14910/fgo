package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fgo.cards.SupportCraft.SupportCraft;
import fgo.patches.Enum.CardTagsEnum;

public class DoppelgangerMaster extends AbstractGameAction {
    public DoppelgangerMaster(){
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0){
            this.isDone = true;
            return;
        }
        AbstractCard target = AbstractDungeon.player.drawPile.getTopCard();
        this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        if (target.hasTag(CardTagsEnum.Noble_Phantasm)) {
            this.addToTop(new MakeTempCardInHandAction(new SupportCraft()));
        } else {
            this.addToBot(new MakeTempCardInHandAction(target.makeStatEquivalentCopy()));
        }
        this.isDone = true;
    }
}
