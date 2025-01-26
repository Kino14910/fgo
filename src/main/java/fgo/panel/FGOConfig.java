package fgo.panel;

import basemod.EasyConfigPanel;
import fgo.FGOMod;

import static fgo.FGOMod.makeID;

public class FGOConfig extends EasyConfigPanel {
    public static final boolean enableColorlessCards = true;
    public static final int testInt = 123;
    public FGOConfig() {
        super(FGOMod.modID, makeID(FGOConfig.class.getSimpleName()));

        setNumberRange("testInt", 0, 1000);


    }
}