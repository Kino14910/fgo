//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//@Deprecated
//
//public class AvalonPower extends BasePower {
//    public static final String POWER_ID = "AvalonPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//
//    public AvalonPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/HealingOfFirePower.png";
//        String path48 = "fgo/images/powers/HealingOfFirePower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        for (AbstractPower power : this.owner.powers) {
//            if (power.type == PowerType.DEBUFF) {
//                for(int i = 0; i < this.amount; ++i) {
//                    this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
//                }
//                break;
//            }
//        }
//    }
//
//    public AbstractPower makeCopy() {
//        return new AvalonPower(this.owner, this.amount);
//    }
//}
