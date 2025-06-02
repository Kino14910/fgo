package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class ImitationGodForce extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ImitationGodForce.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/ImitationGodForce.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/ImitationGodForce_p.png";

    public ImitationGodForce() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(7, 1);
        setMagic(4);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        }

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
