package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static fgo.FGOMod.makeID;

public class TheOneWhoRevealsTruthPower extends BasePower {
    public static final String POWER_ID = makeID(TheOneWhoRevealsTruthPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TheOneWhoRevealsTruthPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
        String path128 = "fgo/images/powers/large/PutOnFakeFacePower.png";
        String path48 = "fgo/images/powers/PutOnFakeFacePower.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    
}
