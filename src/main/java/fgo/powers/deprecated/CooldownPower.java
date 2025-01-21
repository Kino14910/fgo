//package fgo.powers.deprecated;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.FontHelper;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//@Deprecated
//
import static fgo.FGOMod.makeID;

//public class CooldownPower extends BasePower {
//    public static final String POWER_ID = "CooldownPower";
//    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
//    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
//    private final AbstractCard card;
//    private static int bombIdOffset;
//    public CooldownPower(AbstractCreature owner, int cardAmt, AbstractCard copyMe) {
//        this.ID = POWER_ID + bombIdOffset;
//        ++bombIdOffset;
//        this.owner = owner;
//        this.amount = cardAmt;
//        this.card = copyMe.makeStatEquivalentCopy();
//        this.card.resetAttributes();
//        this.type = PowerType.DEBUFF;
//
//        String path128 = "fgo/images/powers/large/BuffBlockPower.png";
//        String path48 = "fgo/images/powers/BuffBlockPower.png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
//        this.name = NAME;
//        this.updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//    }
//
//    @Override
//    public boolean canPlayCard(AbstractCard card) {
//        return !card.cardID.equals(this.card.cardID);
//    }
//
//    @Override
//    public void atStartOfTurn() {
//        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
//        }
//    }
//
//    public AbstractPower makeCopy() {
//        return new CooldownPower(this.owner, this.amount, this.card);
//    }
//}
