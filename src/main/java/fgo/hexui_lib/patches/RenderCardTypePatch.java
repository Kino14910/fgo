package fgo.hexui_lib.patches;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import fgo.hexui_lib.interfaces.CustomCardTypeLocation;
import fgo.hexui_lib.util.FloatPair;
import javassist.CannotCompileException;
import javassist.CtBehavior;


@SpirePatch(clz = AbstractCard.class, method = "renderType")
public class RenderCardTypePatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"font", "text", "renderColor"}
    )
    public static SpireReturn Insert(AbstractCard card, SpriteBatch sb, BitmapFont font, String text, Color renderColor) {
        if (card instanceof CustomCardTypeLocation) {
            CustomCardTypeLocation locationCard = (CustomCardTypeLocation) card;
            FloatPair location = locationCard.getCardTypeLocation(new FloatPair(0f, -22f), false);

            FontHelper.renderRotatedText(sb, font, text,
                    card.current_x,
                    card.current_y + location.y * card.drawScale * Settings.scale,
                    0.0F, -1.0F * card.drawScale * Settings.scale,
                    card.angle, false,
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
                    FontHelper.class, "renderRotatedText");

            // the `new ArrayList<Matcher>()` specifies the prerequisites before the line can be matched -
            // the LineFinder will search for all the prerequisites in order before it will match the finalMatcher -
            // since we didn't specify any prerequisites here, the LineFinder will simply find the first expression
            // that matches the finalMatcher.
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}

