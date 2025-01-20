package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import fgo.action.FgoNpAction;

import static fgo.FGOMod.makeID;


import static fgo.FGOMod.makeID;

public class ArchetypeORTPower extends BasePower {
    public static final String POWER_ID = makeID(ArchetypeORTPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ArchetypeORTPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new StarGainPower(owner, 10), 10));
        addToBot(new FgoNpAction(3));
    }

    public AbstractPower makeCopy() {return new ArchetypeORTPower(owner);}
}
