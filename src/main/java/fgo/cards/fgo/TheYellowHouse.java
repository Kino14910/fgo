package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import static fgo.characters.CustomEnums.Foreigner;
import fgo.powers.CursePower;
import fgo.powers.EvasionPower;
import fgo.utils.CardStats;

public class TheYellowHouse extends FGOCard {
    public static final String ID = makeID(TheYellowHouse.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public TheYellowHouse() {
        super(ID, INFO);
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
