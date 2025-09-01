package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import fgo.characters.Master;

public class UpdateMasterImgAction extends AbstractGameAction {
    public UpdateMasterImgAction() {
    }

    public void update() {
        if (AbstractDungeon.player instanceof Master) {
            AbstractDungeon.player.img = ImageMaster.loadImage("fgo/images/character/Romani_Archaman.png");
        }

        this.isDone = true;
    }
}