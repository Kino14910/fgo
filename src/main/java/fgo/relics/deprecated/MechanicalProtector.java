package fgo.relics.deprecated;

import static fgo.FGOMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.characters.CustomEnums.FGOCardColor;
import fgo.relics.BaseRelic;

public class MechanicalProtector extends BaseRelic {
    private static final String NAME = "MechanicalProtector";
	public static final String ID = makeID(NAME);
    public MechanicalProtector() {
        super(ID, NAME, FGOCardColor.FGO, RelicTier.UNCOMMON, LandingSound.FLAT);
        counter = 3;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.POWER && counter > 0) {
            --counter;
            if (counter == 0) {
                setCounter(-2);
                description = DESCRIPTIONS[1];
                tips.clear();
                tips.add(new PowerTip(name, description));
                initializeTips();
            }

            flash();
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            addToBot(new ApplyPowerAction(randomMonster, AbstractDungeon.player, new StunMonsterPower(randomMonster)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void setCounter(int setCounter) {
        counter = setCounter;
        if (setCounter <= 0) {
            usedUp();
        }
    }
}
