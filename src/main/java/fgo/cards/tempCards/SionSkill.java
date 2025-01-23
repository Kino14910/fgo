package fgo.cards.tempCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

@AutoAdd.Ignore
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
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new DiscardAction(p, p, 1, false));
        this.addToBot(new BetterDrawPileToHandAction(this.magicNumber));
    }
}
