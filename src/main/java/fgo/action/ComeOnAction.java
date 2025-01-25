package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import fgo.powers.BurnDamagePower;

public class ComeOnAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AttackEffect effect;

    public ComeOnAction(AbstractCard card, AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    public void update() {
//        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
//            this.card.calculateCardDamage((AbstractMonster)this.target);
//            this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
            if (this.target.hasPower("BurnDamagePower")) {
                int BurnAmt = this.target.getPower("BurnDamagePower").amount;
                this.addToTop(new ApplyPowerAction(this.target, this.source, new BurnDamagePower(this.target, BurnAmt), BurnAmt));
            }
        }

        this.isDone = true;
    }
}
