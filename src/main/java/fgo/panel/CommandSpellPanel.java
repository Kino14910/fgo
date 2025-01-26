package fgo.panel;

import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;

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
    public void onLoad(Integer commandSpellCount) {
         if (commandSpellCount == null) {
            return;
        }
        CommandSpellPanel.commandSpellCount = commandSpellCount;
    }
}
