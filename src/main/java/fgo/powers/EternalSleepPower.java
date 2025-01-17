package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EternalSleepPower extends AbstractPower {
    public static final String POWER_ID = "EternalSleepPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;

    public EternalSleepPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;

        String path128 = "fgo/images/powers_Master/EternalSleepPower84.png";
        String path48 = "fgo/images/powers_Master/EternalSleepPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    /*public void atStartOfTurn() {
        this.flash();
        this.addToBot(new PressEndTurnButtonAction());
    }*/

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractPower power : this.owner.powers) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
            break;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {
        return new EternalSleepPower(this.owner);
    }
}
