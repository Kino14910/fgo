//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//@Deprecated
//
import static fgo.FGOMod.makeID;

//public class DefenseUpPower extends BasePower {
//    public static final String POWER_ID = "DefenseUpPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    public DefenseUpPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/DefenseUpPower.png";
//        String path48 = "fgo/images/powers/DefenseUpPower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        this.updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//    }
//
//    @Override
//    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
//        return this.calculateDamageTakenAmount(damage, type);
//    }
//
//    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
//        return type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS ? damage - this.amount : damage;
//    }
//
//    @Override
//    public int onAttacked(DamageInfo info, int damageAmount) {
//        boolean willLive = this.calculateDamageTakenAmount((float)damageAmount, info.type) < (float)this.owner.currentHealth;
//        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && willLive) {
//            this.flash();
//            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//        }
//
//        return damageAmount;
//    }
//
//    public AbstractPower makeCopy() {
//        return new DefenseUpPower(this.owner, this.amount);
//    }
//}
