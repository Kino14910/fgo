package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.ArchetypeORTPower;
import fgo.relics.BaseRelic;

public class ArchetypeORT extends BaseRelic {
    private static final String NAME = "ArchetypeORT";
	public static final String ID = makeID(NAME);
    public ArchetypeORT() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArchetypeORTPower(AbstractDungeon.player)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.actNum >= 2;
    }
}
