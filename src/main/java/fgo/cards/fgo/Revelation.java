package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.DefenseDownPower;
import fgo.util.CardStats;

public class Revelation extends FGOCard {
    public static final String ID = makeID(Revelation.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public Revelation() {
        super(ID, INFO);
        setMagic(4, 1);
        setExhaust();
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        if (!m.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1, AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, this.magicNumber), this.magicNumber));
    }
}
