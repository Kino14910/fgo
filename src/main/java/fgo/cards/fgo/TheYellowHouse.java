package fgo.cards.fgo;

import static fgo.characters.CustomEnums.Foreigner;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.CursePower;
import fgo.powers.EvasionPower;
public class TheYellowHouse extends FGOCard {
    public static final String ID = makeID(TheYellowHouse.class.getSimpleName());
    public TheYellowHouse() {
        super(ID, 2, CardType.POWER, CardTarget.SELF, CardRarity.UNCOMMON);
        setCostUpgrade(1);
        shuffleBackIntoDrawPile = true;
        tags.add(Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, 1)));
    }
}


