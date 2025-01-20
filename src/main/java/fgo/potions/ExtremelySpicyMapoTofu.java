package fgo.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.utils.compression.lzma.Base;
import fgo.cards.colorless.potionCards.Kaleidoscope;
import fgo.cards.colorless.potionCards.TheBlackGrail;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

import static fgo.FGOMod.makeID;

public class ExtremelySpicyMapoTofu extends BasePotion {
    public static final String ID = makeID(ExtremelySpicyMapoTofu.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    public ExtremelySpicyMapoTofu(){
        super(ID, 0, PotionRarity.RARE, PotionSize.BOTTLE, Color.ORANGE, Color.RED, null);
        this.labOutlineColor = NOBLE;
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgo:np")),
                BaseMod.getKeywordDescription("fgo:np")));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgo:np_damage")),
                BaseMod.getKeywordDescription("fgo:np_damage")));
    }

    @Override
    public void use(AbstractCreature target) {
        InputHelper.moveCursorToNeutralPosition();
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new Kaleidoscope());
        stanceChoices.add(new TheBlackGrail());
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public int getPotency(int ascensionLevel) {return 0;}

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0];
    }

    @Override
    public AbstractPotion makeCopy() {return new ExtremelySpicyMapoTofu();}
}
