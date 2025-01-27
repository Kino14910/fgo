package fgo.cards.noblecards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class Sevendrive extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Sevendrive.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Sevendrive.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Sevendrive_p.png";

    public Sevendrive() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(3, 1);
        setNP(10);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
        this.addToBot(new VFXAction(new DieDieDieEffect(), 0.7F));
        this.addToBot(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
        for (int i = 0; i < 8; ++i) {
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
        this.addToBot(new FgoNpAction(np));
    }
}
