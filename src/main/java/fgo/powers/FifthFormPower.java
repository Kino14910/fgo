package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FifthFormPower extends BasePower {
    public static final String POWER_ID = makeID(FifthFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private boolean justApplied = false;
   
    public FifthFormPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, false, owner, amount); 
        if (upgraded) {
            justApplied = true;
        }
    }

    @Override
    public void updateDescription() {
        if (justApplied) {
            if (amount <= 1) {
                description = DESCRIPTIONS[3];
            } else {
                description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[4];
            }
        } else {
            if (amount <= 1) {
                description = DESCRIPTIONS[0];
            } else {
                description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
            }
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY)) {
            flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if ((card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY) && card.type == AbstractCard.CardType.ATTACK) {
            if (justApplied) {
                return type == DamageInfo.DamageType.NORMAL ? damage * 0.8F : damage;
            } else {
                return type == DamageInfo.DamageType.NORMAL ? damage * 0.5F : damage;
            }
        }

        return atDamageGive(damage, type);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    
}
