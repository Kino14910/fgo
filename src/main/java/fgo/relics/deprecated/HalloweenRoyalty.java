package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

import fgo.cards.tempCards.BlessedRegenerate;
import fgo.cards.tempCards.BrilliantEscort;
import fgo.cards.tempCards.PureCoordinate;
import fgo.cards.tempCards.SionSkill;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.relics.BaseRelic;

public class HalloweenRoyalty extends BaseRelic {
    private static final String NAME = "HalloweenRoyalty";
	public static final String ID = makeID(NAME);
    private boolean canUse = false;
    public HalloweenRoyalty() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onTrigger() {
        description = DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom && counter < 4) {
            flash();
            counter = 4;
        }
    }

    @Override
    public void onEquip() {
        counter = 4;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (counter == -1) {
            counter += 2;
        } else {
            ++counter;
        }

        if (counter == 5) {
            canUse = true;
        }
    }

    protected void onRightClick() {
        if (counter > 0 && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            counter = 0;
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
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

            addToBot(new ChooseOneAction(stanceChoices));
            canUse = false;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
