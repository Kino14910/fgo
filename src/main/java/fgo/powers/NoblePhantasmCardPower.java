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

public class NoblePhantasmCardPower extends AbstractPower {
    public static final String POWER_ID = "NoblePhantasmCardPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private final AbstractCard card;
    public NoblePhantasmCardPower(AbstractCreature owner, AbstractCard copyMe) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/PutOnFakeFacePower84.png";
        String path48 = "fgo/images/powers_Master/PutOnFakeFacePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        this.updateDescription();
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
