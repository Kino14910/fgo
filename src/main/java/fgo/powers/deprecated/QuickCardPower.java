//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import fgo.powers.CriticalDamageUpPower;
//
//@Deprecated
//
//public class QuickCardPower extends BasePower {
//    public static final String POWER_ID = "QuickCardPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//
//    public QuickCardPower(AbstractCreature owner) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/QuickCardPower.png";
//        String path48 = "fgo/images/powers/QuickCardPower.png";
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
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type != AbstractCard.CardType.ATTACK) {
//            this.flash();
//            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CriticalDamageUpPower(this.owner, 1), 1));
//        }
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }
//
//    public AbstractPower makeCopy() {
//        return new QuickCardPower(this.owner);
//    }
//}
