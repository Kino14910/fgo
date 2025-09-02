package fgo.relics;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.GutsPower;

public class AstrologicalTeapot extends BaseRelic {
    private static final String NAME = "AstrologicalTeapot";
	public static final String ID = makeID(NAME);
    public AstrologicalTeapot() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onUsePotion() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GutsPower(AbstractDungeon.player, 10, 1), 10));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }
}
