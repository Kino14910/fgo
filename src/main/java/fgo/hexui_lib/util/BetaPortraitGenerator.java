package fgo.hexui_lib.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BetaPortraitGenerator {
    private static final HashMap<String, ArrayList<RenderLayer>> pregenBetaPortraits;

    static {
        pregenBetaPortraits = new HashMap<>();
    }

    public static ArrayList<RenderLayer> generate(String cardID, boolean isBigCard) {
        if (pregenBetaPortraits.containsKey(cardID + (isBigCard ? "1024" : "512"))) {
            return pregenBetaPortraits.get(cardID + (isBigCard ? "1024" : "512"));
        }

        ArrayList<RenderLayer> cardArtLayers = new ArrayList<>();
        String betaFolder = "images/cards/512/beta/";
        if (isBigCard) {
            betaFolder = "images/cards/1024/beta/";
        }

        Random rng = LangUtil.getSeededRandom(cardID);
        int beta_count = 4;
        int gradient_count = 8;
        int pattern_count = 14;
        int starburst_count = 6;

        Texture texture = ImageMaster.loadImage(betaFolder + "gradient_" + rng.nextInt(gradient_count) + ".png");
        Color col = getRandomColor(rng);
        col.a = 1f;
        cardArtLayers.add(new RenderImageLayer(texture, col));

        int layers = rng.nextInt(3) + 3;

        for (int i = 0; i < layers; i++) {
            switch (rng.nextInt(3)) {
                case 0:
                    texture = ImageMaster.loadImage(betaFolder + "gradient_" + rng.nextInt(gradient_count) + ".png");
                    break;
                case 1:
                    texture = ImageMaster.loadImage(betaFolder + "pattern_" + rng.nextInt(pattern_count) + ".png");
                    break;
                case 2:
                    texture = ImageMaster.loadImage(betaFolder + "starburst_" + rng.nextInt(starburst_count) + ".png");
                    break;
            }
            col = getRandomColor(rng);
            col.a = (layers - i + 1f) / (layers + 1f);
            cardArtLayers.add(new RenderImageLayer(texture, col));
        }
        texture = ImageMaster.loadImage(betaFolder + "beta_" + rng.nextInt(beta_count) + ".png");
        col = getRandomColor(rng);
        col.a = 0.5f;
        cardArtLayers.add(new RenderImageLayer(texture, col));

        pregenBetaPortraits.put(cardID + (isBigCard ? "1024" : "512"), cardArtLayers);

        return cardArtLayers;
    }

    private static Color getRandomColor(Random rng) {
        Color c = Color.WHITE.cpy();
        c.r = rng.nextFloat();
        c.g = rng.nextFloat();
        c.b = rng.nextFloat();
        c.a = rng.nextFloat();

        return c;
    }
}
