package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FifthFormPower extends AbstractPower {
    public static final String POWER_ID = "FifthFormPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private boolean justApplied = false;
    public FifthFormPower(AbstractCreature owner, int amount, boolean upgraded) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        if (upgraded) {
            this.justApplied = true;
        }
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/FifthFormPower84.png";
        String path48 = "fgo/images/powers_Master/FifthFormPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (justApplied) {
            if (this.amount <= 1) {
                this.description = DESCRIPTIONS[3];
            } else {
                this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[4];
            }
        } else {
            if (this.amount <= 1) {
                this.description = DESCRIPTIONS[0];
            } else {
                this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY)) {
            this.flash();
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

        return this.atDamageGive(damage, type);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    public AbstractPower makeCopy() {
        return new FifthFormPower(this.owner, this.amount, true);
    }
}
