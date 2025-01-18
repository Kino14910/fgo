package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.cards.colorless.ignore.SoulOfWaterChannels;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class CrownedWithLife extends FGOCard {
    public static final String ID = makeID(CrownedWithLife.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -2
    );
    public CrownedWithLife() {
        super(ID, INFO);
        setMagic(2, 1);
        cardsToPreview = new SoulOfWaterChannels();
        setExhaust();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrownedWithLife();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void triggerWhenDrawn() {
        addToTop(new MakeTempCardInHandAction(cardsToPreview, magicNumber));
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
}
