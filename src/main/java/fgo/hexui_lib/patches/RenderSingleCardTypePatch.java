package fgo.hexui_lib.patches;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import fgo.hexui_lib.interfaces.CustomCardTypeLocation;
import fgo.hexui_lib.util.FloatPair;
import javassist.CannotCompileException;
import javassist.CtBehavior;


@SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
public class RenderSingleCardTypePatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"label", "CARD_TYPE_COLOR", "card"}
    )
    public static SpireReturn Insert(SingleCardViewPopup view, SpriteBatch sb, String label, Color CARD_TYPE_COLOR, AbstractCard card) {
        if (card instanceof CustomCardTypeLocation) {
            CustomCardTypeLocation locationCard = (CustomCardTypeLocation) card;
            FloatPair location = locationCard.getCardTypeLocation(new FloatPair(3f, -40f), true);

            FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, label,
                    Settings.WIDTH / 2.0F + location.x * Settings.scale, Settings.HEIGHT / 2.0F + location.y * Settings.scale,
                    Settings.CREAM_COLOR);

            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        // This is the abstract method from SpireInsertLocator that will be used to find the line
        // numbers you want this patch inserted at
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            // finalMatcher is the line that we want to insert our patch before -
            // in this example we are using a `MethodCallMatcher` which is a type
            // of matcher that matches a method call based on the type of the calling
            // object and the name of the method being called. Here you can see that
            // we're expecting the `end` method to be called on a `SpireBatch`
            Matcher finalMatcher = new Matcher.MethodCallMatcher(
                    FontHelper.class, "renderFontCentered");

            // the `new ArrayList<Matcher>()` specifies the prerequisites before the line can be matched -
            // the LineFinder will search for all the prerequisites in order before it will match the finalMatcher -
            // since we didn't specify any prerequisites here, the LineFinder will simply find the first expression
            // that matches the finalMatcher.
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}

