package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;

import fgo.characters.CustomEnums.FGOCardColor;

public class FanNight extends BaseRelic {
    private static final String NAME = "FanNight";
	public static final String ID = makeID(NAME);
    public static final int WEAKENED_AMT = 2;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public FanNight() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onBlockBroken(final AbstractCreature m) {
        flash();
        addToBot(new RelicAboveCreatureAction(m, this));
        addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, WEAKENED_AMT, false), WEAKENED_AMT));
    }
}
