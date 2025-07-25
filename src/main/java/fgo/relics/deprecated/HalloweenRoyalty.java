package fgo.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import fgo.cards.tempCards.BlessedRegenerate;
import fgo.cards.tempCards.BrilliantEscort;
import fgo.cards.tempCards.PureCoordinate;
import fgo.cards.tempCards.SionSkill;
import fgo.patches.Enum.FGOCardColor;
import fgo.relics.BaseRelic;

import java.util.ArrayList;

import static fgo.FGOMod.makeID;

public class HalloweenRoyalty extends BaseRelic {
    private static final String NAME = "HalloweenRoyalty";
	public static final String ID = makeID(NAME);
    private boolean canUse = false;
    public HalloweenRoyalty() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onTrigger() {
        this.description = this.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom && this.counter < 4) {
            this.flash();
            this.counter = 4;
        }
    }

    @Override
    public void onEquip() {
        this.counter = 4;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 5) {
            this.canUse = true;
        }
    }

    protected void onRightClick() {
        if (this.counter > 0 && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new BrilliantEscort());
            stanceChoices.add(new PureCoordinate());
            stanceChoices.add(new BlessedRegenerate());
            if (AbstractDungeon.actNum >= 2) {
                stanceChoices.add(new SionSkill());
            }

            if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
                for (AbstractCard c : stanceChoices) {
                    c.upgrade();
                }
            }

            this.addToBot(new ChooseOneAction(stanceChoices));
            canUse = false;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HalloweenRoyalty();
    }
}
