package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.red.Metallicize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import fgo.powers.IgnoresInvincibilityPower;

public class IgnoresInvincibilityAction extends AbstractGameAction {
    private int timesAmount = 0;
    private final AbstractPlayer p = AbstractDungeon.player;


    public IgnoresInvincibilityAction(AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            String[] powerIds = {
                IntangiblePower.POWER_ID,
                PlatedArmorPower.POWER_ID,
                Metallicize.ID
            };
            for (String id : powerIds) {
                if (this.target.hasPower(id)) {
                    this.addToBot(new RemoveSpecificPowerAction(this.target, p, id));
                    timesAmount++;
                }
            }

            timesAmount += amount;

            addToBot(new ApplyPowerAction(p, p, new IgnoresInvincibilityPower(p, timesAmount)));
            // addToBot(new ApplyPowerAction(p, p, new VigorPower(p, timesAmount)));

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }


}
