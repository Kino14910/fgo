package fgo.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.relics.MisoPotato;

import static fgo.FGOMod.makeID;

public class ItsInevitablePower extends BasePower {
    public static final String POWER_ID = makeID(ItsInevitablePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int damage;
    private final int raise;

    public ItsInevitablePower(AbstractCreature owner, int amount, int damage, int raise) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "BurningPower");
        this.damage = damage;
        this.raise = raise;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.damage);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.damage, this.raise);
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        if (AbstractDungeon.player.hasRelic(MisoPotato.ID)) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(mo, this.owner, new BurnDamagePower(mo, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        this.damage += this.raise;
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        //updateDescription();
    }

    
}
