//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//@Deprecated
//
//public class ChildrenHeroPower extends BasePower {
//    public static final String POWER_ID = "ChildrenHeroPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    public ChildrenHeroPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        if (this.amount >= 2) {
//            this.amount = 2;
//        }
//        this.type = PowerType.BUFF;
//
//        String path128 = "fgo/images/powers/large/PeacockStrutPower.png";
//        String path48 = "fgo/images/powers/PeacockStrutPower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//        }
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        if (isPlayer) {
//            for (AbstractCard c : AbstractDungeon.player.hand.group) {
//                if (!c.isEthereal) {
//                    c.retain = true;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onInitialApplication() {
//        AbstractDungeon.player.gameHandSize = 0;
//    }
//
//    @Override
//    public void atEndOfRound() {
//        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
//    }
//
//    @Override
//    public void onRemove() {
//        AbstractDungeon.player.gameHandSize = 5;
//        this.addToBot(new DrawCardAction(this.owner, 5));
//    }
//
//    public AbstractPower makeCopy() {return new ChildrenHeroPower(this.owner, this.amount);}
//}
