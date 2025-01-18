package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NightmarePower;
import fgo.patches.Enum.CardTagsEnum;

@Deprecated
public class FacelessMoonAction extends AbstractGameAction {
    private final AbstractPlayer p;
    public FacelessMoonAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = (AbstractPlayer)target;
    }

    @Override
    public void update() {
        if (this.p.hand.isEmpty()) {
            this.isDone = true;
        } else {
            for (AbstractCard theSize : AbstractDungeon.player.hand.group) {
                if (!theSize.hasTag(CardTagsEnum.Noble_Phantasm)) {
                    this.addToTop(new ApplyPowerAction(this.p, this.p, new NightmarePower(this.p, this.amount, theSize)));
                    this.isDone = true;
                }
            }
        }
    }
}
