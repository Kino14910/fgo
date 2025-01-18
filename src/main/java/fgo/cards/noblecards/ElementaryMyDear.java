package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.IgnoreDefensePower;

public class ElementaryMyDear extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ElementaryMyDear.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/ElementaryMyDear.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/ElementaryMyDear_p.png";

    public ElementaryMyDear() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(2, 2);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ElementaryMyDear();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IgnoreDefensePower(p, this.magicNumber), this.magicNumber));
    }
}
