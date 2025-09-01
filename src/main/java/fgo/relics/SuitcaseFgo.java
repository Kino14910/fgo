package fgo.relics;

import static fgo.FGOMod.makeID;

import fgo.patches.Enum.FGOCardColor;

public class SuitcaseFgo extends BaseRelic{
    private static final String NAME = "SuitcaseFgo";
    public static final String ID = makeID(NAME);
    public SuitcaseFgo() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // 此处逻辑在Master中
    // @Override
    // public void receiveOnBattleStart(AbstractRoom abstractRoom) {
    //     addToBot(new FgoNpAction(20));
    //     flash();
    // }
}
