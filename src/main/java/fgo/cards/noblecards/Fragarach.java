package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.FragarachPower;

public class Fragarach extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Fragarach.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Fragarach.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Fragarach_p.png";

    public Fragarach() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setDamage(15, 5);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FragarachPower(p, damage)));
    }
}
