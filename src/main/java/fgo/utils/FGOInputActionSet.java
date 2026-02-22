package fgo.utils;

import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;

public class FGOInputActionSet {
    public static InputAction nobleDeckAction;
    private static final String NOBLE_DECK_KEY = "NOBLE_DECK";
    public static Prefs prefs = InputActionSet.prefs;

    public static void load() {
        nobleDeckAction = new InputAction(prefs.getInteger(NOBLE_DECK_KEY, 49)); // 49对应N键
    }

    public static void save() {
        prefs.putInteger(NOBLE_DECK_KEY, nobleDeckAction.getKey());
        prefs.flush();
    }

    public static void resetToDefaults() {
        nobleDeckAction.remap(49);
    }
}
