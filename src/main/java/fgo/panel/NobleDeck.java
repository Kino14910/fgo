package fgo.panel;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import basemod.TopPanelItem;
import fgo.patches.Enum.CardTagsEnum;
import fgo.util.NobleCardGroup;

public class NobleDeck extends TopPanelItem {
    private static final Texture IMG = new Texture("fgo/images/ui/NobleTopPanel.png");
    public static final String ID = "fgo:NobleDeck";
    public static NobleCardGroup nobleCards = new NobleCardGroup();

    public NobleDeck() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        CardCrawlGame.sound.play("DECK_OPEN");
    }

    public static void addCard(AbstractCard card) {
        if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
            nobleCards.addToTop(card);
        }
    }
}
