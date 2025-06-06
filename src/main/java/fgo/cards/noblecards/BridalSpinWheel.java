package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.StarPower;

public class BridalSpinWheel extends AbsNoblePhantasmCard {
    public static final String ID = makeID(BridalSpinWheel.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/BridalSpinWheel.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/BridalSpinWheel_p.png";

    public BridalSpinWheel() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(3, 1);
        setStar(10, 10);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
    }
}
