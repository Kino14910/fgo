package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.vfx.HKExpungeVFXAction;

public class HeroicKingAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AttackEffect effect;

    public HeroicKingAction(AbstractCard card, AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster) this.target);
            this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
            this.addToTop(new HKExpungeVFXAction((AbstractMonster) this.target));
        }

        this.isDone = true;
    }
}
