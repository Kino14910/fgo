package fgo.cards.derivative.mash;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import fgo.action.IgnoresInvincibilityAction;
import fgo.cards.AbsNoblePhantasmCard;

public class RayProofKyrielight extends AbsNoblePhantasmCard {
    public static final String ID = makeID(RayProofKyrielight.class.getSimpleName());

    public RayProofKyrielight() {
        super(ID, CardType.ATTACK, CardTarget.ALL_ENEMY, 1);
        setDamage(20, 20);
        setMagic(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IgnoresInvincibilityAction(m));

        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, 
            monster -> new VulnerablePower(monster, magicNumber, false))
        );

        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (m.type != AbstractMonster.EnemyType.BOSS) {
            for (AbstractPower pow : m.powers) {
                if (pow.type == AbstractPower.PowerType.BUFF) {
                    addToBot(new RemoveSpecificPowerAction(m, p, pow));
                }
            }
        }
    }
}
