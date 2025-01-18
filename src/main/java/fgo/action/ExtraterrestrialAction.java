package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.powers.DefenseDownPower;

public class ExtraterrestrialAction extends AbstractGameAction {
    private final AbstractMonster m;
    private final int magicNumber;
    public ExtraterrestrialAction(AbstractMonster monster, int amount) {
        this.m = monster;
        this.magicNumber = amount;
    }

    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).type == AbstractCard.CardType.ATTACK) {
            this.addToTop(new ApplyPowerAction(this.m, AbstractDungeon.player, new DefenseDownPower(this.m, this.magicNumber), this.magicNumber));
        }

        this.isDone = true;
    }
}
