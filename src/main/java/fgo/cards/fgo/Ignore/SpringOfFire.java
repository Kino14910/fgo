package fgo.cards.fgo.Ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.GutsPower;
import fgo.powers.NPRatePower;
import fgo.powers.SpringOfFirePower;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class SpringOfFire extends FGOCard {
    public static final String ID = makeID(SpringOfFire.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public SpringOfFire() {
        super(ID, INFO);
        setMagic(3, 3);
        setEthereal();
        setExhaust();
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpringOfFire();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(GutsPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, this.magicNumber, 3), this.magicNumber));
        }
        if (!p.hasPower(SpringOfFirePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new SpringOfFirePower(p, 20)));
        }
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, this.magicNumber), this.magicNumber));
    }
}
