package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.CursePower;
import fgo.powers.NPOverChargePower;

public class RoadlessCamelot extends AbsNoblePhantasmCard {
    public static final String ID = makeID(RoadlessCamelot.class.getSimpleName());

    public RoadlessCamelot() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY, 1);
        setDamage(24, 8);
        setMagic(1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        
        addToBot(new ApplyPowerAction(p, p, new CursePower(m, p, 3)));
        addToBot(new ApplyPowerAction(p, p, new NPOverChargePower(p, 1)));
    }
}
