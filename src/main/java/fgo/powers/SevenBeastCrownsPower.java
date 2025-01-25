package fgo.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import static fgo.FGOMod.makeID;

public class SevenBeastCrownsPower extends BasePower {
    public static final String POWER_ID = makeID(SevenBeastCrownsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int BeastIdOffset;
    public SevenBeastCrownsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "DracoPower");
        ID = POWER_ID + BeastIdOffset;
        BeastIdOffset++;
        if(amount <= 0) {
            SevenBeastCrownsAction();
        }
    }

    @Override
    public void updateDescription() {
        if (amount <= 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
    
    public void SevenBeastCrownsAction() {
        addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, 3), 3));
        addToBot(new ApplyPowerAction(owner, owner, new PlatedArmorPower(owner, 4), 4));
        addToBot(new ApplyPowerAction(owner, owner, new RegenPower(owner, 4), 4));
        addToBot(new ApplyPowerAction(owner, owner, new BerserkPower(owner, 1), 1));
        addToBot(new ApplyPowerAction(owner, owner, new DrawPower(owner, 1), 1));
        addToBot(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, 2), 2));
        addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, 2), 2));
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
            if (amount == 1) {
                SevenBeastCrownsAction();
            }
        }
    }

    public AbstractPower makeCopy() {
        return new SevenBeastCrownsPower(owner, amount);
    }
}
