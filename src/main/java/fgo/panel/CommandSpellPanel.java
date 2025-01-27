package fgo.panel;

import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.awt.*;

public class CommandSpellPanel implements CustomSavable<Integer> {
    // The field value you wish to save.
    public static int commandSpellCount = 3;
    public static Texture CommandSpell;


    @Override
    public Integer onSave() {
        return commandSpellCount;
    }

    @Override
    public void onLoad(Integer count) {
         if (count == null) {
            return;
        }
        commandSpellCount = count;
    }

    public static void reset(){
        commandSpellCount = 3;
        CommandSpell = ImageMaster.loadImage("fgo/images/ui/CommandSpell/CommandSpell3.png");
    }
}
