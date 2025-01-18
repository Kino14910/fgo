package fgo.cards.colorless.ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class RepairSpiritOrigin extends FGOCard {
    public static final String ID = makeID(RepairSpiritOrigin.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public static final String IMG = "fgo/images/cards/power/CommandSpellGuts.png";
    public RepairSpiritOrigin() {
        super(ID, INFO, IMG);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new RepairSpiritOrigin();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 30));
    }
}
