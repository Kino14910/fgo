package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import fgo.hexui_lib.CardRenderer;
import fgo.hexui_lib.interfaces.CustomCardPortrait;


@SpirePatch(clz = SingleCardViewPopup.class, method = "renderPortrait")
public class RenderSingleCardPortraitPatch {
    @SpireInsertPatch(
            rloc = 0,
            localvars = {"card"}
    )
    public static SpireReturn Insert(SingleCardViewPopup view, final SpriteBatch sb, AbstractCard card) {
        if (card instanceof CustomCardPortrait) {
            float drawX = Settings.WIDTH / 2.0F - 512f;
            float drawY = Settings.HEIGHT / 2.0F - 512f;

            CardRenderer.renderPortrait(sb, card, drawX, drawY, true);
            return SpireReturn.Return(null);

        }
        return SpireReturn.Continue();
    }
}

