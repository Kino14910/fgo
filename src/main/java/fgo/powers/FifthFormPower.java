package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FifthFormPower extends BasePower {
    public static final String POWER_ID = makeID(FifthFormPower.class.getSimpleName());

    public FifthFormPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount); 
        this.amount2 = upgraded ? 35 : 50;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = amount == 1 
            ? String.format(DESCRIPTIONS[0], amount2) 
            : String.format(DESCRIPTIONS[1], amount, amount2);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == CardType.ATTACK && (card.target == CardTarget.ENEMY || card.target == CardTarget.SELF_AND_ENEMY)) {
            flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0f;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }

    @Override
    public float atDamageGive(float damage, DamageType type, AbstractCard card) {
        if ((card.target == CardTarget.ENEMY || card.target == CardTarget.SELF_AND_ENEMY) && card.type == CardType.ATTACK) {
            return type != DamageType.THORNS ? damage * (100 - amount2) / 100.0f : damage;
        }

        return atDamageGive(damage, type);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }
}
