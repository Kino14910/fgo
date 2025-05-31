package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IgnoresInvincibilityAction extends AbstractGameAction {
    private int timesAmount;
    private final DamageInfo info;

    public IgnoresInvincibilityAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            if (!this.target.isDying && !this.target.isDead && this.target.currentBlock > 0) {
                this.target.loseBlock();
                ++timesAmount;
            }

            if (this.target.hasPower("Plated Armor")) {
                this.addToBot(new RemoveSpecificPowerAction(this.target, AbstractDungeon.player, "Plated Armor"));
                ++timesAmount;
            }

            if (this.target.hasPower("Metallicize")) {
                this.addToBot(new RemoveSpecificPowerAction(this.target, AbstractDungeon.player, "Metallicize"));
                ++timesAmount;
            }

            if (this.target.hasPower("Intangible")) {
                this.addToBot(new RemoveSpecificPowerAction(this.target, AbstractDungeon.player, "Intangible"));
                ++timesAmount;
            }

            for(int i = 0; i < timesAmount; ++i) {
                this.target.damage(this.info);
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
