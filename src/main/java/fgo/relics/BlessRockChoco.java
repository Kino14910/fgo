package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BlessRockChoco extends CustomRelic {
    public static final String ID = "BlessRockChoco";
    private static final String IMG = "fgo/images/relics_Master/BlessRockChoco.png";
    private static final String IMG_OTL = "fgo/images/relics_Master/outline/BlessRockChoco.png";
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)

    public BlessRockChoco() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.costForTurn >= 3) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            drawnCard.costForTurn -= 1;
            drawnCard.isCostModifiedForTurn = true;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlessRockChoco();
    }
}
