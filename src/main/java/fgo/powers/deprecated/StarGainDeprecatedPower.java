package fgo.powers.deprecated;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.patches.Enum.CardTagsEnum;
import fgo.powers.HeroicKingPower;

import java.util.Objects;

@Deprecated
public class StarGainDeprecatedPower extends AbstractPower {
    public static final String POWER_ID = "StarGainPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    public StarGainDeprecatedPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "img/powers_Master/StarGainPower84.png";
        String path48 = "img/powers_Master/StarGainPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount >= 10) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //你有10颗暴击星时才能暴击。
        if (!card.hasTag(CardTagsEnum.Noble_Phantasm) && this.amount >= 10) {
            //红牌暴击。
            if (this.owner.hasPower("StarHunterPower") && Objects.equals(card.glowColor, CardHelper.getColor(155, 22, 22))) {
                return this.atDamageGive1(damage, type);
            }
            //绿牌暴击。
            if (this.owner.hasPower("PrimevalRunePower") && Objects.equals(card.glowColor, CardHelper.getColor(22, 88, 11))) {
                return this.atDamageGive2(damage, type);
            }
            //特例：翡翠的魅力、20暴击星
            if (card.cardID.equals("CharismaOfTheJade") && this.amount >= 20) {
                //红牌暴击。
                if (this.owner.hasPower("StarHunterPower") && Objects.equals(card.glowColor, CardHelper.getColor(155, 22, 22))) {
                    return this.atDamageGive4(damage, type);
                }
                //绿牌暴击。
                if (this.owner.hasPower("PrimevalRunePower") && Objects.equals(card.glowColor, CardHelper.getColor(22, 88, 11))) {
                    return this.atDamageGive5(damage, type);
                }
                //正常暴击。
                return this.atDamageGive3(damage, type);
            }
            //正常暴击。
            return this.atDamageGive(damage, type);
        }
        return damage;
    }

    //正常暴击。
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower("CriticalDamageUpPower")) {
            int CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount;
            return type == DamageInfo.DamageType.NORMAL ? damage * (150.0F + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage;
    }

    //红牌暴击伤害。
    public float atDamageGive1(float damage, DamageInfo.DamageType type) {
        if (this.owner.hasPower("StarHunterPower")) {
            int ShAmt = (this.owner.getPower("StarHunterPower")).amount;
            int CrAmt = 0;
            //暴击威力提高。
            if (this.owner.hasPower("CriticalDamageUpPower")) {
                CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount;
            }
            return type == DamageInfo.DamageType.NORMAL ? damage * (150.0F + ShAmt + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage;
    }

    //绿牌暴击伤害。
    public float atDamageGive2(float damage, DamageInfo.DamageType type) {
        if (this.owner.hasPower("PrimevalRunePower")) {
            int PrAmt = (this.owner.getPower("PrimevalRunePower")).amount;
            int CrAmt = 0;
            //暴击威力提高。
            if (this.owner.hasPower("CriticalDamageUpPower")) {
                CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount;
            }
            return type == DamageInfo.DamageType.NORMAL ? damage * (150.0F + PrAmt + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage;
    }

    //翡翠的魅力暴击伤害。
    public float atDamageGive3(float damage, DamageInfo.DamageType type) {
        //暴击威力提高。
        if (this.owner.hasPower("CriticalDamageUpPower")) {
            int CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount * 2;
            return type == DamageInfo.DamageType.NORMAL ? damage * (300.0F + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    //翡翠的魅力红牌暴击伤害。
    public float atDamageGive4(float damage, DamageInfo.DamageType type) {
        if (this.owner.hasPower("StarHunterPower")) {
            int ShAmt = (this.owner.getPower("StarHunterPower")).amount * 2;
            int CrAmt = 0;
            //暴击威力提高。
            if (this.owner.hasPower("CriticalDamageUpPower")) {
                CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount * 2;
            }
            return type == DamageInfo.DamageType.NORMAL ? damage * (300.0F + ShAmt + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    //翡翠的魅力绿牌暴击伤害。
    public float atDamageGive5(float damage, DamageInfo.DamageType type) {
        if (this.owner.hasPower("PrimevalRunePower")) {
            int PrAmt = (this.owner.getPower("PrimevalRunePower")).amount * 2;
            int CrAmt = 0;
            //暴击威力提高。
            if (this.owner.hasPower("CriticalDamageUpPower")) {
                CrAmt = (this.owner.getPower("CriticalDamageUpPower")).amount * 2;
            }
            return type == DamageInfo.DamageType.NORMAL ? damage * (300.0F + PrAmt + CrAmt) / 100.0F : damage;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && this.amount >= 10) {
            if (card.cardID.equals("CharismaOfTheJade") && this.amount >= 20) {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 20));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 10));
            }
            if (this.owner.hasPower("HeroicKingPower")) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new HeroicKingPower(this.owner, 1), 1));
            }
        }
    }

    public AbstractPower makeCopy() {return new StarGainDeprecatedPower(this.owner, this.amount);}
}
