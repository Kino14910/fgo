package fgo.utils.modifiers;

import static fgo.FGOMod.uiPath;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import basemod.abstracts.AbstractCardModifier;

public class DisplayStarModifier extends AbstractCardModifier {
    public static String MOD_ID = "fgo:DisplayStarModifier";
    private static final Texture tex = ImageMaster.loadImage(uiPath("DisplayModifier"));
    private final int amount;
    public DisplayStarModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.renderIcon(card, tex, 0, 40, Color.PINK, "+" + amount + " STAR", FontHelper.cardTypeFont, 0, 0, Color.WHITE);
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DisplayStarModifier(this.amount);
    }
}