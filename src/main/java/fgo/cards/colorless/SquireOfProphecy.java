package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.SquireOfProphecyPower;
import fgo.utils.CardStats;

public class SquireOfProphecy extends FGOCard {
    public static final String ID = makeID(SquireOfProphecy.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public SquireOfProphecy() {
        super(ID, INFO);
        setMagic(2, 1);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SquireOfProphecyPower(p, this.magicNumber), this.magicNumber));
    }
}
