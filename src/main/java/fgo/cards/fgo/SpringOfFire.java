package fgo.cards.fgo;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.GutsPower;
import fgo.powers.NPRatePower;
import fgo.powers.SpringOfFirePower;
import fgo.util.CardStats;

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
        setEthereal(true, false);
        setExhaust();
        this.tags.add(CardTags.HEALING);
    }

    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(GutsPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new GutsPower(p, this.magicNumber, 3), this.magicNumber));
        }
        if (!p.hasPower(SpringOfFirePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new SpringOfFirePower(p, magicNumber)));
        }
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, this.magicNumber), this.magicNumber));
    }
}
