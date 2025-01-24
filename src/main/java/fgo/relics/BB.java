package fgo.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class BB extends BaseRelic {
    private static final String NAME = "BB";
	public static final String ID = makeID(NAME);
    public BB() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        flash();
        grayscale = true;
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        int roll = MathUtils.random(1);

        if (roll == 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
            return;
        }

        int roll_debuff = MathUtils.random(2);
        switch (roll_debuff) {
            case 0:
                addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, true), 1));
                break;
            case 1:
                addToBot(new ApplyPowerAction(p, p, new WeakPower(p, 1, true), 1));
                break;
            default:
                addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 1, true), 1));
                break;
        }
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BB();
    }
}
