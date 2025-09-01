package fgo.panel;

import static fgo.FGOMod.makeID;

import basemod.EasyConfigPanel;
import fgo.FGOMod;

public class FGOConfig extends EasyConfigPanel {
    public static boolean enableColorlessCards = true;
    public static boolean enableEmiya = true;


    public FGOConfig() {
        super(FGOMod.modID, makeID(FGOConfig.class.getSimpleName()));
    }

}