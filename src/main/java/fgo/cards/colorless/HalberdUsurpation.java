package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class HalberdUsurpation extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("HalberdUsurpation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/HalberdUsurpation.png";
    private static final int COST = 2;
    public static final String ID = "HalberdUsurpation";
    public HalberdUsurpation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 20;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HalberdUsurpation();
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
        if (!m.isDeadOrEscaped() & m.hasPower(StrengthPower.POWER_ID)) {
            int HuAmt = (m.getPower(StrengthPower.POWER_ID)).amount * 2;
            this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -HuAmt), -HuAmt, true, AbstractGameAction.AttackEffect.NONE));
            if (!m.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, HuAmt), HuAmt, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}
