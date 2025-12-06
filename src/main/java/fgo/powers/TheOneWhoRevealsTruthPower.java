package fgo.powers;

import static fgo.FGOMod.makeID;
import static fgo.FGOMod.powerPath;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class TheOneWhoRevealsTruthPower extends BasePower {
    public static final String POWER_ID = makeID(TheOneWhoRevealsTruthPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;

    public TheOneWhoRevealsTruthPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, false, owner);
        String path128 = powerPath("large/PutOnFakeFacePower");
        String path48 = powerPath("PutOnFakeFacePower");
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    
}
