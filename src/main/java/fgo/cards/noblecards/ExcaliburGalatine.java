package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.SunlightPower;

public class ExcaliburGalatine extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ExcaliburGalatine.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/ExcaliburGalatine.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/ExcaliburGalatine_p.png";

    public ExcaliburGalatine() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(3, 1);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExcaliburGalatine();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SunlightPower(p, 3), 3));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
        //this.addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50), 50));
    }
}
