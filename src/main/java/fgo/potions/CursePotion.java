package fgo.potions;

import static fgo.FGOMod.makeID;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;

import basemod.BaseMod;
import fgo.powers.CursePower;

public class CursePotion extends BasePotion {
    public static final String ID = makeID(CursePotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    public CursePotion(){
        super(ID, 0, PotionRarity.UNCOMMON, PotionSize.SPHERE, Color.PURPLE, Color.RED, Color.DARK_GRAY);
        this.labOutlineColor = NOBLE;
        this.isThrown = true;
        this.targetRequired = true;
    }

    @Override
    public void addAdditionalTips() {
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgo:curse")),
                BaseMod.getKeywordDescription("fgo:curse")));
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new CursePower(target, potency)));
    }
    
    @Override
    public String getDescription() {
        return String.format(DESCRIPTIONS[0], potency);
    }
}
