package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import fgo.hexui_lib.interfaces.CustomCardPortrait;

/*, paramtypez = { SpriteBatch.class, float.class, float.class }*/
@SpirePatch(clz = AbstractCard.class, method = "renderPortraitFrame")
public class RenderCardPortraitFramePatch {
    @SpireInsertPatch(
            rloc = 0,
            localvars = {"renderColor"}
    )
    public static SpireReturn Insert(AbstractCard card, final SpriteBatch sb, float x, float y, Color renderColor) {
        if (card instanceof CustomCardPortrait) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}

