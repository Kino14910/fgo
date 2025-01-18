package fgo.action;

import fgo.cards.noblecards.HollowHeartAlbion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fgo.patches.Enum.CardTagsEnum;
import fgo.powers.NoblePhantasmCardPower;

public class NoblePhantasmSelectAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("NPText").TEXT;
    private final boolean upgraded;
    public NoblePhantasmSelectAction(boolean upgraded, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            if (AbstractDungeon.player.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                group.addToTop(new HollowHeartAlbion());
            } else {
                for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                    if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
                        if (card.upgraded) {
                            card.upgrade();
                            group.addToTop(card);
                        } else {
                            group.addToTop(card);
                        }
                    }
                }
            }

            for (AbstractCard c : group.group) {
                UnlockTracker.markCardAsSeen(c.cardID);
            }

            if (this.upgraded) {
                for (AbstractCard c : group.group) {
                    c.upgrade();
                }
            }

            AbstractDungeon.gridSelectScreen.open(group, 1, NPTEXT[2], false, false, true, false);
            this.tickDuration();
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard cStudy = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
                if (AbstractDungeon.gridSelectScreen.selectedCards.get(0).upgraded || this.upgraded) {
                    cStudy.upgrade();
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy, this.amount));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            this.tickDuration();
        }
    }
}
