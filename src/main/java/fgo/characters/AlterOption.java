package fgo.characters;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

public class AlterOption extends AbstractCampfireOption {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("fgo:AlterOption")).TEXT;
    public AlterOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = ImageMaster.loadImage("img/ui/tune.png");
    }

    public void useOption() {
        if (this.usable) AbstractDungeon.effectList.add(new AlterOptionEffect());
    }
}