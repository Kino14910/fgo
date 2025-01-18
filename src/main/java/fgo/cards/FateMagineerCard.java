package fgo.cards;

import fgo.hexui_lib.interfaces.CustomCardPortrait;
import fgo.hexui_lib.interfaces.CustomCardTypeLocation;
import fgo.hexui_lib.util.*;

import java.util.ArrayList;

import static fgo.util.GeneralUtils.removePrefix;
import static fgo.util.TextureLoader.getCardTextureString;

public abstract class FateMagineerCard extends FGOCard implements CustomCardPortrait, CustomCardTypeLocation {
    public static ArrayList<RenderLayer> outerCircuits512 = new ArrayList<>();
    public static ArrayList<RenderLayer> outerCircuits1024 = new ArrayList<>();
    public static ArrayList<RenderLayer> outerMagic512 = new ArrayList<>();
    public static ArrayList<RenderLayer> outerMagic1024 = new ArrayList<>();
    public static boolean decoRenderLayersInitialized = false;
    public static RenderLayer improvementSlotsPanel512;
    public static RenderLayer improvementSlotsPanel1024;
    private final ArrayList<RenderLayer> portraitLayers512 = new ArrayList<>();
    private final ArrayList<RenderLayer> portraitLayers1024 = new ArrayList<>();
    public ArrayList<RenderLayer> cardArtLayers512 = new ArrayList<>();
    public ArrayList<RenderLayer> cardArtLayers1024 = new ArrayList<>();
    public FateMagineerCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color) {
        super(ID, cost, cardType, target, rarity, color, getCardTextureString(removePrefix(ID), cardType));
        initializeDecoRenderLayers();
    }
//    public FateMagineerCard(final String id, final String name, final String img, final int cost, final String rawDescription,
//                            final CardType type, final CardColor color,
//                            final CardRarity rarity, final CardTarget target) {
//        super(id, name, img, cost, rawDescription, type, color, rarity, target);
//
//        initializeDecoRenderLayers();
//    }

    private void initializeDecoRenderLayers() {
        if (decoRenderLayersInitialized) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            outerCircuits512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_circuits_outer_" + i + ".png")));
            outerMagic512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_magic_outer_" + i + ".png")));

            outerCircuits1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_circuits_outer_" + i + ".png")));
            outerMagic1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_magic_outer_" + i + ".png")));
        }

        //improvementSlotsPanel512 = new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/improvement_slots_panel.png"));
        //improvementSlotsPanel1024 = new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/improvement_slots_panel.png"));

        decoRenderLayersInitialized = true;
    }

    public ArrayList<RenderLayer> getPortraitLayers512() {
        portraitLayers512.clear();
        addCardArtLayers512(portraitLayers512);
        portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_fullportrait_desc_shadow.png")));
        //portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_cardtype_gray.png")));
        portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_fullportrait_inner_shadow.png")));
        portraitLayers512.add(improvementSlotsPanel512);

        switch (this.type) {
            case ATTACK:
                portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_attack_fullportait_gray.png")));
                break;
            case POWER:
                portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_power_fullportait_gray.png")));
                break;
            case SKILL:
            default:
                portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/512/bg_skill_fullportait_gray.png")));
        }

        return portraitLayers512;
    }

    public ArrayList<RenderLayer> getPortraitLayers1024() {
        portraitLayers1024.clear();
        addCardArtLayers1024(portraitLayers1024);
        portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_fullportrait_desc_shadow.png")));
        //portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("magineerResources/images/1024/bg_cardtype_gray.png")));
        portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_fullportrait_inner_shadow.png")));
        portraitLayers1024.add(improvementSlotsPanel1024);

        switch (this.type) {
            case ATTACK:
                portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_attack_fullportait_gray.png")));
                break;
            case POWER:
                portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_power_fullportait_gray.png")));
                break;
            case SKILL:
            default:
                portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgo/images/NobleResources/1024/bg_skill_fullportait_gray.png")));
        }

        return portraitLayers1024;
    }

    public FloatPair getCardTypeLocation(FloatPair pair, boolean isBigCard) {
        if (isBigCard) {
            pair.y += 432f;
        } else {
            pair.y += 224f;
        }
        return pair;
    }

    protected void generateBetaArt() {
        //logger.info(this.cardID+".generateBetaArt()");
        cardArtLayers512 = BetaPortraitGenerator.generate(this.cardID, false);
        cardArtLayers1024 = BetaPortraitGenerator.generate(this.cardID, true);
    }

    public void addCardArtLayers512(ArrayList<RenderLayer> portraitLayers) {
        portraitLayers.addAll(cardArtLayers512);
    }

    public void addCardArtLayers1024(ArrayList<RenderLayer> portraitLayers) {
        portraitLayers.addAll(cardArtLayers1024);
    }
}