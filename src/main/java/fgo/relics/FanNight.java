package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FanNight extends CustomRelic {
    public static final String ID = "FanNight";
    private static final String IMG = "fgo/images/relics_Master/FanNight.png";
    private static final String IMG_OTL = "fgo/images/relics_Master/outline/FanNight.png";
    private static boolean usedThisCombat = false;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public FanNight() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    @Override
    public void onBlockBroken(AbstractCreature m) {
        if (!usedThisCombat) {
            this.flash();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(m, this));
            AbstractDungeon.player.heal((int) (AbstractDungeon.player.maxHealth * 0.2));
            usedThisCombat = true;
            this.grayscale = true;
        }
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public void onVictory() {
        this.pulse = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FanNight();
    }
}
