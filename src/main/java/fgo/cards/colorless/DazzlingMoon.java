package fgo.cards.colorless;

import static fgo.FGOMod.cardPath;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.MillenniumCastlePower;
public class DazzlingMoon extends FGOCard {
    public static final String ID = makeID(DazzlingMoon.class.getSimpleName());
    public DazzlingMoon() {
        super(ID, 3, CardType.POWER, CardTarget.SELF, CardRarity.RARE, CardColor.COLORLESS);
        setMagic(3);
        setInnate(false, true);
        portraitImg = ImageMaster.loadImage(cardPath("power/MillenniumCastle"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MillenniumCastlePower(p, magicNumber), magicNumber));
    }
}


