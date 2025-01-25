package fgo.cards.optionCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class RestoreSpiritOrigin extends FGOCard {
    public static final String ID = makeID(RestoreSpiritOrigin.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public RestoreSpiritOrigin() {
        super(ID, INFO);
        setNP(300);
    }
    
    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new HealAction(p, p, p.maxHealth));
        this.addToBot(new FgoNpAction(np));
    }
}
