package fgo.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static fgo.FGOMod.makeID;

public class DesterrennachtPower extends BasePower {
    public static final String POWER_ID = makeID(DesterrennachtPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    private static int IdOffset;
    
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public DesterrennachtPower(AbstractCreature owner, int amount) {
        super(POWER_ID + IdOffset, TYPE, TURN_BASED, owner, amount, "EndOfADreamPower");
        IdOffset++;
        this.name = DesterrennachtPower.NAME;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        if (this.amount == 1) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StunMonsterPower((AbstractMonster) this.owner)));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID(DesterrennachtPower.class.getSimpleName()));
        NAME = DesterrennachtPower.powerStrings.NAME;
        DESCRIPTIONS = DesterrennachtPower.powerStrings.DESCRIPTIONS;
    }
}
