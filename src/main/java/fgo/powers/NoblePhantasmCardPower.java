package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class NoblePhantasmCardPower extends BasePower {
    public static final String POWER_ID = makeID(NoblePhantasmCardPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractCard card;
 
    public NoblePhantasmCardPower(AbstractCreature owner, AbstractCard copyMe) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();

        String path128 = "img/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "img/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1];
    }

    @Override
    public void onSpecificTrigger() {
        this.addToBot(new MakeTempCardInHandAction(this.card.makeStatEquivalentCopy(), 1));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public AbstractPower makeCopy() {
        return new NoblePhantasmCardPower(this.owner, this.card);
    }
}
