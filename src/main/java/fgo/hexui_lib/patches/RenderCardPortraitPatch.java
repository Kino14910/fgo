package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;

import fgo.hexui_lib.CardRenderer;
import fgo.hexui_lib.interfaces.CustomCardPortrait;


@SpirePatch(clz = AbstractCard.class, method = "renderPortrait")
public class RenderCardPortraitPatch {
    @SpireInsertPatch(
            rloc = 0,
            localvars = {"renderColor"}
    )
    public static SpireReturn Insert(AbstractCard card, SpriteBatch sb, Color renderColor) {
        if (card instanceof CustomCardPortrait) {
            sb.setColor(renderColor);
            float drawX = card.current_x - 256.0F;
            float drawY = card.current_y - 256.0F;

            CardRenderer.renderPortrait(sb, card, drawX, drawY, false);
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}

