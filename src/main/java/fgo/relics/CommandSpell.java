package fgo.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fgo.cards.colorless.CommandSpellGuts;
import fgo.cards.colorless.ReleaseNoblePhantasm;
import fgo.cards.colorless.RepairSpiritOrigin;

import java.util.ArrayList;

public class CommandSpell extends CustomRelic {
    public static final String ID = "CommandSpell";
    private static final String IMG = "fgo/images/relics_Master/CommandSpell.png";
    private static final String IMG_OTL = "fgo/images/relics_Master/outline/CommandSpell.png";
    private boolean ClickStart = false;
    private boolean Click = false;
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public CommandSpell() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.FLAT);
        this.counter = 3;
    }

    /*@Override
    public void onChestOpen(boolean bossChest) {
        if (bossChest && this.counter < 3) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            if (this.counter == 2) {
                ++this.counter;
            } else {
                this.counter += 2;
            }
        }
    }*/

    protected void onRightClick() {
        if (this.counter > 0 && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new RepairSpiritOrigin());
            stanceChoices.add(new ReleaseNoblePhantasm());

            this.addToBot(new ChooseOneAction(stanceChoices));
            --this.counter;
        }
    }

    public void update() {
        super.update();
        if (this.ClickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Click = true;
            }

            this.ClickStart = false;
        }

        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.ClickStart = true;
        }

        if (this.Click) {
            this.Click = false;
            onRightClick();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onTrigger() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new CommandSpellGuts());

        this.addToBot(new ChooseOneAction(stanceChoices));
        this.setCounter(0);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CommandSpell();
    }
}
