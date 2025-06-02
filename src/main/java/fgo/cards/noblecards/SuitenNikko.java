package fgo.cards.noblecards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.action.SuitenNikkoAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.util.TextureLoader;

public class SuitenNikko extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SuitenNikko.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/SuitenNikko.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/SuitenNikko_p.png";

    public SuitenNikko() {
        super(ID, AbstractCard.CardType.POWER, AbstractCard.CardTarget.ALL_ENEMY);
        setMagic(35, 15);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    public float getTitleFontSize() {
        if (Settings.language != Settings.GameLanguage.ZHS) {
            return 16.0f;
        }
        return -1.0f;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SuitenNikkoAction(1));
        addToBot(new FgoNpAction(magicNumber));
    }
}

