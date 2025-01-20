package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.action.NightlessCharismaAction;


import static fgo.FGOMod.makeID;

public class NightlessCharismaPower extends BasePower {
    public static final String POWER_ID = makeID(NightlessCharismaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public NightlessCharismaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        String path128 = "img/powers_Master/SunlightPower84.png";
        String path48 = "img/powers_Master/SunlightPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new NightlessCharismaAction(this.amount, AbstractCard.CardType.ATTACK));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public AbstractPower makeCopy() {
        return new NightlessCharismaPower(this.owner, this.amount);
    }
}
