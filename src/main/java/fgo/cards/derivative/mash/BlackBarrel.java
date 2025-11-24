package fgo.cards.derivative.mash;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.utils.CardStats;
public class BlackBarrel extends FGOCard {
    public static final String ID = makeID(BlackBarrel.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String upgradeName = cardStrings.EXTENDED_DESCRIPTION[0];
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );
    public BlackBarrel() {
        super(ID, INFO);
        setDamage(9, 3);
        setExhaust();
    }

    @Override
    protected void upgradeName() {
        timesUpgraded++;
        upgraded = true;
        name = upgradeName;
        initializeTitle();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (!mo.isDying && !mo.isDead && mo.currentBlock > 0) {
            this.baseDamage += mo.currentBlock;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
