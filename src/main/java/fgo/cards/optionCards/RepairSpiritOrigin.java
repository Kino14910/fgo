package fgo.cards.optionCards;

import static fgo.FGOMod.cardPath;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.utils.CardStats;

public class RepairSpiritOrigin extends FGOCard {
    public static final String ID = makeID(RepairSpiritOrigin.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public static final String IMG = cardPath("power/CommandSpellGuts");
    public RepairSpiritOrigin() {
        super(ID, INFO, IMG);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 30));
    }
}
