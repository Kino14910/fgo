package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;


import static fgo.FGOMod.makeID;

public class SevenBeastCrownsPower extends BasePower {
    public static final String POWER_ID = makeID(SevenBeastCrownsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int BeastIdOffset;
    public SevenBeastCrownsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount, "DracoPower");
        this.ID = POWER_ID + BeastIdOffset;
        BeastIdOffset++;
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, 3), 3));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, 4), 4));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, 4), 4));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new BerserkPower(this.owner, 1), 1));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawPower(this.owner, 1), 1));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 2), 2));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, 2), 2));
            }
        }
    }

    public AbstractPower makeCopy() {
        return new SevenBeastCrownsPower(this.owner, this.amount);
    }
}
