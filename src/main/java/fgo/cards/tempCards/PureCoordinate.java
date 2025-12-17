package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.InvincibilityPower;

public class PureCoordinate extends FGOCard {
    public static final String ID = makeID(PureCoordinate.class.getSimpleName());

    public PureCoordinate() {
        super(ID, -2, CardType.POWER, CardTarget.NONE, CardRarity.SPECIAL, CardColor.COLORLESS);
        setMagic(2, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new InvincibilityPower(p, magicNumber)));
    }
}
