//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.LoseHPAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
//
//@Deprecated
//
//public class NextLoseHpPower extends BasePower {
//    public static final String POWER_ID = "NextLoseHpPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//
//    public NextLoseHpPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.DEBUFF;
//
//        String path128 = "fgo/images/powers/large/NextLoseHpPower.png";
//        String path48 = "fgo/images/powers/NextLoseHpPower.png";
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
//    public void atStartOfTurn() {
//        this.flash();
//        AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.owner.hb.cX, this.owner.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
//        this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }
//
//    public AbstractPower makeCopy() {
//        return new NextLoseHpPower(this.owner, this.amount);
//    }
//}
