package fgo.cards;

import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.util.CardStats;

public class SionSkill extends FGOCard {
    public static final String ID = makeID(SionSkill.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public SionSkill() {
        super(ID, INFO);
        setMagic(1, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SionSkill();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new DiscardAction(p, p, 1, false));
        this.addToBot(new BetterDrawPileToHandAction(this.magicNumber));
    }
}
