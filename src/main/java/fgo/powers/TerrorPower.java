package fgo.powers;

import static fgo.FGOMod.makeID;

import java.util.Random;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TerrorPower extends BasePower implements NonStackablePower{
    public static final String POWER_ID = makeID(TerrorPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    private final Random random = new Random();

    public TerrorPower(AbstractCreature owner, int turns, int chance) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, turns, "EndOfADreamPower");
        this.amount2 = chance;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount2, amount); 
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ReducePowerAction(owner, owner, this, 1));
        // amount%概率给眩晕
        if (random.nextInt(100) < amount2) {
            CardCrawlGame.sound.play("POWER_STUN");
            addToBot(new ApplyPowerAction(owner, owner, new StunMonsterPower((AbstractMonster) owner)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            if (!this.owner.isPlayer && AbstractDungeon.player.hasPower(VSTerrorDamageUpPower.POWER_ID)) {
                return damage * (1 + AbstractDungeon.player.getPower(VSTerrorDamageUpPower.POWER_ID).amount / 100.0f);
            }
        }
        return damage;
    }
}
