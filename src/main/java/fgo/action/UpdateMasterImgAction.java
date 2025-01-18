package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import fgo.characters.master;

public class UpdateMasterImgAction extends AbstractGameAction {
    public UpdateMasterImgAction() {
    }

    public void update() {
        if (AbstractDungeon.player instanceof master) {
            AbstractDungeon.player.img = ImageMaster.loadImage("img/char_Master/Romani_Archaman.png");
        }

        this.isDone = true;
    }
}