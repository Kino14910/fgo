//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.CardHelper;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//import java.util.Objects;
//
//@Deprecated
//
import static fgo.FGOMod.makeID;

//public class BusterCardPower extends BasePower {
//    public static final String POWER_ID = "BusterCardPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//
//    public BusterCardPower(AbstractCreature owner) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.type = PowerType.BUFF;
//
//        String path128 = "img/powers_Master/BusterCardPower84.png";
//        String path48 = "img/powers_Master/BusterCardPower32.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0];
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }
//
//    @Override
//    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
//        if (Objects.equals(card.glowColor, CardHelper.getColor(22, 88, 11))) {
//            return type == DamageInfo.DamageType.NORMAL ? damage + 1.0F : damage;
//        }
//        return damage;
//    }
//
//    @Override
//    public float modifyBlock(float blockAmount, AbstractCard card) {
//        if (Objects.equals(card.glowColor, CardHelper.getColor(22, 88, 11))) {
//            return blockAmount + 1;
//        }
//
//        return this.modifyBlock(blockAmount);
//    }
//
//    public AbstractPower makeCopy() {
//        return new BusterCardPower(this.owner);
//    }
//}
