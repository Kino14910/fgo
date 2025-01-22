package fgo.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.CardTagsEnum;

@SpirePatch(clz = CardGroup.class, method = "initializeDeck")
public class NoblePhantasmDeckPatch {
    @SpireInsertPatch(loc = 1052)
    public static void Insert(CardGroup __instance, CardGroup masterDeck) {
        __instance.group.removeIf(card -> card.hasTag(CardTagsEnum.Noble_Phantasm));
    }
}
