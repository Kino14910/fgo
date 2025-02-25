package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class HalberdUsurpation extends FGOCard {
    public static final String ID = makeID(HalberdUsurpation.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public HalberdUsurpation() {
        super(ID, INFO);
        setDamage(15, 5);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int StrengthAmount = 0;
        if (mo.hasPower(StrengthPower.POWER_ID)) {
            StrengthAmount += (mo.getPower(StrengthPower.POWER_ID)).amount;
        }

        int realBaseDamage = this.baseDamage;
        this.baseDamage += StrengthAmount;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExpungeVFXAction(m));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        if (m.isDeadOrEscaped() & m.hasPower(StrengthPower.POWER_ID)) {
            return;
        }
        int HuAmt = (m.getPower(StrengthPower.POWER_ID)).amount * 2;
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -HuAmt), -HuAmt, true, AbstractGameAction.AttackEffect.NONE));
        if (!m.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, HuAmt), HuAmt, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
