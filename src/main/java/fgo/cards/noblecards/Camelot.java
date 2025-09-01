package fgo.cards.noblecards;

import static com.megacrit.cardcrawl.core.Settings.language;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class Camelot extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Camelot.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Camelot.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Camelot_p.png";

    public Camelot() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setBlock(25, 10);
        setMagic(6, 3);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    
    @Override
    public float getTitleFontSize() {
        if (language == Settings.GameLanguage.ZHS && upgraded) {
            return 24.0F;
        }

        return -1.0F;
    }

    @Override
    protected void upgradeName() {
        timesUpgraded++;
        upgraded = true;
        name = "已然遥远的理想之城";
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, magicNumber)));
    }
}
