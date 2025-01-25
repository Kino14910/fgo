package fgo.cards.noblecards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import fgo.action.FetchFailnaughtAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.CursePower;

public class FetchFailnaught extends AbsNoblePhantasmCard {
    public static final String ID = makeID(FetchFailnaught.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/FetchFailnaught.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/FetchFailnaught_p.png";

    public FetchFailnaught() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(30, 8);
        setMagic(3);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
        } else {
            this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
        }
        for(i = 0; i < 5; ++i) {
            this.addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(m, p, new CursePower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new FetchFailnaughtAction(m, p));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (mo.hasPower(CursePower.POWER_ID)) {
            int CurAmt = mo.getPower(CursePower.POWER_ID).amount;
            this.baseDamage += this.baseDamage / 10 * CurAmt;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
}
