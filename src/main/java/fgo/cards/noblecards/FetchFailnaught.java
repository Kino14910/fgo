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
import fgo.powers.CursePower;

public class FetchFailnaught extends AbsNoblePhantasmCard {
    public static final String ID = makeID(FetchFailnaught.class.getSimpleName());

    public FetchFailnaught() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY, 1);
        setDamage(30, 8);
        setMagic(3);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
        } else {
            addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
        }
        for(i = 0; i < 5; i++) {
            addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        }

        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new CursePower(m, p, magicNumber)));
        addToBot(new FetchFailnaughtAction(m, p));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        if (mo.hasPower(CursePower.POWER_ID)) {
            int CurAmt = mo.getPower(CursePower.POWER_ID).amount;
            baseDamage += baseDamage / 10 * CurAmt;
        }
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }
}
