package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.ArtsPerformancePower;

import static com.megacrit.cardcrawl.core.Settings.language;

public class Overload extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Overload.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Overload.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Overload_p.png";
//    public static final String ID = "Overload";
    public Overload() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(30, 8);
        setMagic(2);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public float getTitleFontSize() {
        if (language == Settings.GameLanguage.ZHS || language == Settings.GameLanguage.ZHT || language == Settings.GameLanguage.JPN) {
            return 24.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtsPerformancePower(p, magicNumber)));
        addToBot(new RemoveAllBlockAction(m, p));
        addToBot(new DamageAction(m, new DamageInfo(m, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }
}
