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

public class DisplayNPModifier extends AbstractCardModifier {
    public static String MOD_ID = "fgo:DisplayNPModifier";
    private static final Texture tex = ImageMaster.loadImage(uiPath("DisplayModifier"));
    private final int amount;
    public DisplayNPModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.renderIcon(card, tex, 0, 0, Color.WHITE, "+" + amount + "% NP", FontHelper.cardTypeFont, 0, 0, Color.WHITE);
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DisplayNPModifier(this.amount);
    }

}