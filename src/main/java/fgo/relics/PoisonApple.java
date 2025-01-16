package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PoisonApple extends CustomRelic {
    public static final String ID = "PoisonApple";
    private static final String IMG = "img/relics_Master/PoisonApple.png";
    private static final String IMG_OTL = "img/relics_Master/outline/PoisonApple.png";

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public PoisonApple() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        //this.flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        this.addToBot(new BouncingFlaskAction(randomMonster, 1, 1));
        return MathUtils.floor(blockAmount);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PoisonApple();
    }
}
