package fgo.cards.fgo;

import static com.megacrit.cardcrawl.core.Settings.language;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.BeyondTheFurthestEndPower;
public class BeyondTheFurthestEnd extends FGOCard {
    public static final String ID = makeID(BeyondTheFurthestEnd.class.getSimpleName());
    public BeyondTheFurthestEnd() {
        super(ID, 2, CardType.POWER, CardTarget.ALL_ENEMY, CardRarity.UNCOMMON);
        setMagic(3, 1);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/power/BeyondTheFurthestEnd.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public float getTitleFontSize() {
        if (language != Settings.GameLanguage.ZHS) {
            return 16.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BeyondTheFurthestEndPower(p, magicNumber)));
    }
}


