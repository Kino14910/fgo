package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.NPRatePower;
import fgo.powers.WallOfSnowflakesPower;
import fgo.util.CardStats;

public class KnightStance extends FGOCard {
    public static final String ID = makeID(KnightStance.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2
    );
    public KnightStance() {
        super(ID, INFO);
        setBlock(10, 3);
        setMagic(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new KnightStance();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new WallOfSnowflakesPower(p, 20), 20));
        addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, magicNumber), magicNumber));
    }
}
