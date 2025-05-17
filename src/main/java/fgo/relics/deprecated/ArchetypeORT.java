package fgo.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.ArchetypeORTPower;
import fgo.relics.BaseRelic;

import static fgo.FGOMod.makeID;

public class ArchetypeORT extends BaseRelic {
    private static final String NAME = "ArchetypeORT";
	public static final String ID = makeID(NAME);
    public ArchetypeORT() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArchetypeORTPower(AbstractDungeon.player)));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.actNum >= 2;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArchetypeORT();
    }
}
