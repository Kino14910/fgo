package fgo.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import static fgo.FGOMod.makeID;

public class ElixirofRejuvenation extends BasePotion {
    public static final String ID = makeID(ElixirofRejuvenation.class.getSimpleName());
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    public ElixirofRejuvenation() {
        super(ID, 10, PotionRarity.RARE, PotionSize.SPHERE, Color.CYAN, Color.CYAN, null);
        this.labOutlineColor = NOBLE;
        this.isThrown = false;
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.potency));
        this.addToBot(new RemoveAllPowersAction(AbstractDungeon.player, true));
    }

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public AbstractPotion makeCopy() {return new ElixirofRejuvenation();}
}