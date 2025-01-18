package fgo.cards.colorless.ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.ArchetypeORTPower;
import fgo.powers.EternalMemoriesPower;
import fgo.powers.InvincibilityPower;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class PureCoordinate extends FGOCard {
    public static final String ID = makeID(PureCoordinate.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public PureCoordinate() {
        super(ID, INFO);
        setMagic(2, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PureCoordinate();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(ArchetypeORTPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new EternalMemoriesPower(p, this.magicNumber), this.magicNumber));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new InvincibilityPower(p, this.magicNumber), this.magicNumber));
        }
    }
}
