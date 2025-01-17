package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class ReplicaAgateram extends FGOCard {
    public static final String ID = makeID(ReplicaAgateram.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public ReplicaAgateram() {
        super(ID, INFO);
        setBlock(6, 4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReplicaAgateram();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new ReducePowerAction(p, p, power, 1));
                this.addToBot(new GainBlockAction(p, p, this.block));
            }
        }
    }
}
