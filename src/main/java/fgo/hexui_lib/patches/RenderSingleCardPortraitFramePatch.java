package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import fgo.hexui_lib.interfaces.CustomCardPortrait;

@SpirePatch(clz = SingleCardViewPopup.class, method = "renderFrame")
public class RenderSingleCardPortraitFramePatch {
    @SpireInsertPatch(
            rloc = 0,
            localvars = {"card"}
    )
    public static SpireReturn Insert(SingleCardViewPopup view, final SpriteBatch sb, AbstractCard card) {
        if (card instanceof CustomCardPortrait) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}

