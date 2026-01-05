package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.CursePower;

public class LockChocolate extends BaseRelic {
    private static final String NAME = LockChocolate.class.getSimpleName();
	public static final String ID = makeID(NAME);
    
    public LockChocolate() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        //counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CursePower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
