package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class MaraPapiyas extends FGOCard {
    public static final String ID = makeID(MaraPapiyas.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public MaraPapiyas() {
        super(ID, INFO);
        setMagic(12, 4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.decreaseMaxHealth(2);
        this.addToBot(new HealAction(p, p, this.magicNumber));
    }
}
