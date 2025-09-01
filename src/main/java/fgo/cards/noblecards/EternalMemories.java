package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.EternalMemoriesPower;

public class EternalMemories extends AbsNoblePhantasmCard {
    public static final String ID = makeID(EternalMemories.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/EternalMemories.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/EternalMemories_p.png";

    public EternalMemories() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(2, 1);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new RemoveDebuffsAction(p));
        addToBot(new ApplyPowerAction(p, p, new EternalMemoriesPower(p, 1)));
    }
}
