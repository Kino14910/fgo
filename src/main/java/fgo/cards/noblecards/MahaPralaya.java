package fgo.cards.noblecards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class MahaPralaya extends AbsNoblePhantasmCard {
    public static final String ID = makeID(MahaPralaya.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/MahaPralaya.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/MahaPralaya_p.png";
//    public static final String ID = "MahaPralaya";
    public MahaPralaya() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(5, 1);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int MahaAmt = 0;
        for (AbstractMonster moo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            for (AbstractPower power : moo.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    ++MahaAmt;
                }
            }
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += MahaAmt;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
        this.addToBot(new VFXAction(new DieDieDieEffect(), 0.7F));
        this.addToBot(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
        for (int i = 0; i < 5; ++i) {
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
    }
}
