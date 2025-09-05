package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.MillenniumCastlePower;
public class MillenniumCastle extends FGOCard {
    public static final String ID = makeID(MillenniumCastle.class.getSimpleName());
    public MillenniumCastle() {
        super(ID, 3, CardType.POWER, CardTarget.SELF, CardRarity.RARE, CardColor.COLORLESS);
        setMagic(3);
        setInnate(false, true);
        this.portraitImg = ImageMaster.loadImage("fgo/images/cards/power/MillenniumCastle.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MillenniumCastlePower(p, this.magicNumber), this.magicNumber));
    }
}


