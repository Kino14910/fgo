package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.ChasmatisPower;
import fgo.util.CardStats;

public class Chasmatis extends FGOCard {
    public static final String ID = makeID(Chasmatis.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public Chasmatis() {
        super(ID, INFO);
        setMagic(1, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chasmatis();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ChasmatisPower(p, magicNumber)));
    }
}
