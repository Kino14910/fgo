//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//@Deprecated
//
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

//public class BusterPerformancePower extends BasePower {
//    public static final String POWER_ID = "BusterPerformancePower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    public BusterPerformancePower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//
//        String path128 = "img/powers_Master/BusterPerformancePower84.png";
//        String path48 = "img/powers_Master/BusterPerformancePower32.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//    }
//
//    @Override
//    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
//        if (card.type == AbstractCard.CardType.ATTACK) {
//            return type == DamageInfo.DamageType.NORMAL ? damage + this.amount : damage;
//        }
//
//        return damage;
//    }
//
//    @Override
//    public float modifyBlock(float blockAmount, AbstractCard card) {
//        if (card.type == AbstractCard.CardType.ATTACK) {
//            return this.modifyBlock(blockAmount);
//        }
//        return blockAmount;
//    }
//
//    @Override
//    public float modifyBlock(float blockAmount) {
//        return blockAmount + this.amount;
//    }
//
//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
//    }
//
//    public AbstractPower makeCopy() {
//        return new BusterPerformancePower(this.owner, this.amount);
//    }
//}
