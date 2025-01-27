package fgo.panel;

import basemod.BaseMod;
import basemod.EasyConfigPanel;
import basemod.ModLabeledToggleButton;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import fgo.FGOMod;

import java.io.IOException;

import static fgo.FGOMod.makeID;

public class FGOConfig extends EasyConfigPanel {
    public static boolean enableColorlessCards = true;
    public static boolean enableEmiya = true;


    public FGOConfig() {
        super(FGOMod.modID, makeID(FGOConfig.class.getSimpleName()));
    }

}