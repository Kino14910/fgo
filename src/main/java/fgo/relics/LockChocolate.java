package fgo.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CursePower;

import static fgo.FGOMod.makeID;

public class LockChocolate extends BaseRelic {
    private static final String NAME = "LockChocolate";
	public static final String ID = makeID(NAME);
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public LockChocolate() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        //this.counter = 0;
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CursePower(AbstractDungeon.player, 1), 1));
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
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LockChocolate();
    }
}
