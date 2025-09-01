package fgo.relics.deprecated;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

import static fgo.FGOMod.makeID;

public class PoisonApple extends BaseRelic {
    private static final String NAME = "PoisonApple";
	public static final String ID = makeID(NAME);
    public PoisonApple() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        //this.flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        this.addToBot(new BouncingFlaskAction(randomMonster, 1, 1));
        return MathUtils.floor(blockAmount);
    }
}
