package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import fgo.cards.AbsNoblePhantasmCard;

public class Failnaught extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Failnaught.class.getSimpleName());

    public Failnaught() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY, 1);
        setDamage(32, 8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveAllBlockAction(m, p));
        if (m.hasPower(ArtifactPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(m, p, ArtifactPower.POWER_ID));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
    }
}
