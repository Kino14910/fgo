package fgo.cards.fgo;

import fgo.action.FgoNpAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class SwordOfSelection extends FGOCard {
    public static final String ID = makeID(SwordOfSelection.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public SwordOfSelection() {
        super(ID, INFO);
        setMagic(2, 1);
        setCasterBackground();
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordOfSelection();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        setNP(p.hand.size());
        addToBot(new FgoNpAction(np, true));
        //addToBot(new ApplyPowerAction(p, p, new ArtsPerformancePower(p, 2), 2));
    }

    @Override
    public void applyPowers() {
        int theSize = AbstractDungeon.player.hand.size();
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        super.applyPowers();
        initializeDescription();
    }
}
