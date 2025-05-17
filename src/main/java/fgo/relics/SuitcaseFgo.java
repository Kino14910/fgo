package fgo.relics;

import basemod.interfaces.OnStartBattleSubscriber;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fgo.action.FgoNpAction;
import fgo.patches.Enum.FGOCardColor;

import static fgo.FGOMod.makeID;

public class SuitcaseFgo extends BaseRelic implements OnStartBattleSubscriber {
    private static final String NAME = "SuitcaseFgo";
    public static final String ID = makeID(NAME);
    public SuitcaseFgo() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SuitcaseFgo();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        this.addToBot(new FgoNpAction(20));
    }
}
