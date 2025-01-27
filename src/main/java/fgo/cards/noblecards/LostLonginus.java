package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class LostLonginus extends AbsNoblePhantasmCard {
    public static final String ID = makeID(LostLonginus.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/LostLonginus.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/LostLonginus_p.png";
//    public static final String ID = "LostLonginus";
    public LostLonginus() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(24, 6);
        setMagic(3);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        for (AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped()) {
                if (m2.hasPower("Invincible")) {
                    this.addToBot(new RemoveSpecificPowerAction(m2, p, "Invincible"));
                }
            }
        }
    }
}
