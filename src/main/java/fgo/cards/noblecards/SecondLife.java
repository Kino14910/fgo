package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class SecondLife extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SecondLife.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/SecondLife.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/SecondLife_p.png";

    public SecondLife() {
        super(ID,CardType.POWER, CardTarget.SELF);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhumeAction(true));
    }
}
