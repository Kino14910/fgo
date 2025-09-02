package fgo.characters;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class CustomEnums {
    @SpireEnum public static AbstractCard.CardTags Foreigner;

    @SpireEnum public static AbstractPlayer.PlayerClass FGO_MASTER;

    public static class FGOCardColor {
        @SpireEnum public static AbstractCard.CardColor FGO;
        @SpireEnum public static AbstractCard.CardColor NOBLE_PHANTASM;
    }

    public static class FGOLibraryType {
        @SpireEnum public static CardLibrary.LibraryType FGO;
        @SpireEnum public static CardLibrary.LibraryType NOBLE_PHANTASM;
    }
}
