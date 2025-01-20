package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.action.FgoNpAction;


import static fgo.FGOMod.makeID;

public class MorningLarkPower extends BasePower {
    public static final String POWER_ID = makeID(MorningLarkPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public MorningLarkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        String path128 = "img/powers_Master/EndOfADreamPower84.png";
        String path48 = "img/powers_Master/EndOfADreamPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
//
//    @Override
//    public void onInitialApplication() {
//        AbstractDungeon.player.gameHandSize -= this.amount;
//    }
//
//    @Override
//    public void onRemove() {
//        AbstractDungeon.player.gameHandSize += this.amount;
//    }
//
//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
//    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new FgoNpAction(-20));
    }

    public AbstractPower makeCopy() {
        return new MorningLarkPower(this.owner, this.amount);
    }
}
