package fgo.patches.Modifier;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DisplayNPModifier extends AbstractCardModifier {
    public static String MOD_ID = "DisplayNPModifier";
    private static final Texture tex = ImageMaster.loadImage("fgo/images/UI_Master/DisplayModifier.png");
    private final int amount;
    public DisplayNPModifier(int amount) {
        this.amount = amount;
    }

    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.renderIcon(card, tex, 0, 0, Color.WHITE, "+" + amount + "% NP", FontHelper.cardTypeFont, 0, 0, Color.WHITE);
    }

    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    public AbstractCardModifier makeCopy() {
        return new DisplayNPModifier(this.amount);
    }

}