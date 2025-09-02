package fgo.patches;


import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.screens.options.InputSettingsScreen;
import com.megacrit.cardcrawl.screens.options.RemapInputElement;

import fgo.utils.FGOInputActionSet;
import javassist.CtBehavior;


public class HotkeyPatches {

    @SpirePatch(
            clz = InputSettingsScreen.class,
            method = "refreshData"
    )
    public static class RefreshData {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"elements"}
        )
        public static void insert(InputSettingsScreen __instance, ArrayList<RemapInputElement> elements) {
            if (!Settings.isControllerMode) {
                elements.add(new RemapInputElement(__instance, "打开宝具卡组", FGOInputActionSet.nobleDeckAction));
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(InputSettingsScreen.class, "maxScrollAmount");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = InputActionSet.class,
            method = "load"
    )
    public static class Load {
        @SpirePrefixPatch
        public static void prefix() {
            FGOInputActionSet.load();
        }
    }

    @SpirePatch(
            clz = InputActionSet.class,
            method = "save"
    )
    public static class Save {
        @SpirePrefixPatch
        public static void prefix() {
            FGOInputActionSet.save();
        }
    }

    @SpirePatch(
            clz = InputActionSet.class,
            method = "resetToDefaults"
    )
    public static class Reset {
        @SpirePrefixPatch
        public static void prefix() {
            FGOInputActionSet.resetToDefaults();
        }
    }

    // @SpirePatch(
    //         clz = InputAction.class,
    //         method = "isJustPressed"
    // )
    // public static class onInitialPress {
    //     @SpirePrefixPatch
    //     public static SpireReturn<Boolean> preventInitialPress(InputAction __instance) {
    //         if (EUIInputManager.isInputTyping()) {
    //             return SpireReturn.Return(false);
    //         }
    //         return SpireReturn.Continue();
    //     }
    // }

    // @SpirePatch(
    //         clz = InputAction.class,
    //         method = "isPressed"
    // )
    // public static class onPress {
    //     @SpirePrefixPatch
    //     public static SpireReturn<Boolean> preventPress(InputAction __instance) {
    //         if (EUIInputManager.isInputTyping()) {
    //             return SpireReturn.Return(false);
    //         }
    //         return SpireReturn.Continue();
    //     }
    // }
}