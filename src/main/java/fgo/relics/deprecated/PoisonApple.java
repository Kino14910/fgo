package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.relics.BaseRelic;

public class PoisonApple extends BaseRelic {
    private static final String NAME = "PoisonApple";
	public static final String ID = makeID(NAME);
    public PoisonApple() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        //flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new BouncingFlaskAction(randomMonster, 1, 1));
        return MathUtils.floor(blockAmount);
    }
}
