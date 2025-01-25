package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.EnergyRegenPower;
import fgo.powers.StarRegenPower;

public class GardenOfAvalon extends AbsNoblePhantasmCard {
    public static final String ID = makeID(GardenOfAvalon.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/GardenOfAvalon.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/GardenOfAvalon_p.png";

    public GardenOfAvalon() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(5, 2);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new StarRegenPower(p, this.magicNumber), this.magicNumber));
    }
}
