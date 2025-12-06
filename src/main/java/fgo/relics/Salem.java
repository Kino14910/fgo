package fgo.relics;

import static fgo.FGOMod.makeID;
import static fgo.characters.CustomEnums.FGO_Foreigner;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import fgo.characters.CustomEnums.FGOCardColor;

public class Salem extends BaseRelic {
    private static final String NAME = "Salem";
    public static final String ID = makeID(NAME);
    
    public Salem() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    private int countFGO_ForeignerCards() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.hasTag(FGO_Foreigner)) {
                count++;
            }
        }
        return count;
    }

    private void updateDescriptionAndTips() {
        if (counter == 0) {
            description = String.format(DESCRIPTIONS[0] + DESCRIPTIONS[1], 1);
        } else {
            description = String.format(DESCRIPTIONS[0] + DESCRIPTIONS[2], 1, counter);
        }

        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void setCounter(int c) {
        counter = c;
        updateDescriptionAndTips();
    }

    @Override
    public void onMasterDeckChange() {
        counter = countFGO_ForeignerCards();
        updateDescriptionAndTips();
    }

    @Override
    public void onEquip() {
        counter = countFGO_ForeignerCards();
        updateDescriptionAndTips();
    }

    @Override
    public void atTurnStart() {
        if (counter > 0) {
            flash();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
                new VigorPower(AbstractDungeon.player, counter), counter));
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }
}
