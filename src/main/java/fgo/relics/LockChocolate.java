package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.CursePower;

public class LockChocolate extends BaseRelic {
    private static final String NAME = LockChocolate.class.getSimpleName();
	public static final String ID = makeID(NAME);
    public AbstractPlayer p = AbstractDungeon.player;
    
    public LockChocolate() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        //counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new CursePower(p, p, 1)));
    }

    @Override
    public void onEquip() {
        ++p.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --p.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
