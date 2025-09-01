package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;

import fgo.hexui_lib.interfaces.CustomCardPortrait;


@SpirePatch(clz = AbstractCard.class, method = "getCardBg")
public class GetCardBgPatch {
    @SpireInsertPatch(
            rloc = 0
    )
    public static SpireReturn<Texture> Insert(AbstractCard card) {
        if (card instanceof CustomCardPortrait) {
            CustomCardPortrait customPortraitCard = (CustomCardPortrait) card;
        }
        return SpireReturn.Continue();
    }
}

