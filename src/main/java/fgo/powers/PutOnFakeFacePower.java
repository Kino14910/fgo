package fgo.powers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import fgo.action.FgoNpAction;

import static fgo.FGOMod.makeID;

public class PutOnFakeFacePower extends BasePower {
    public static final String POWER_ID = makeID(PutOnFakeFacePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int np;
    private int hp;
    private int artifact;

    public PutOnFakeFacePower(AbstractCreature owner, int np, int hp, int artifact) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
        this.np = np;
        this.hp = hp;
        this.artifact = artifact;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        int roll = MathUtils.random(2);
        switch (roll) {
            case 0:
                addToBot(new FgoNpAction(np));
                break;
            case 1:
                addToBot(new HealAction(owner, owner, hp));
                break;
            case 2:
                addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, artifact)));
                break;
            default:
                break;
        }
    }
}
