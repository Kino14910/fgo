package fgo.cards.colorless.mash;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

import static com.megacrit.cardcrawl.core.Settings.language;

public class BlackBarrel extends FGOCard {
    public static final String ID = makeID(BlackBarrel.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1
    );
    public BlackBarrel() {
        super(ID, INFO);
        setDamage(14, 4);
        setExhaust();
    }


    @Override
    protected void upgradeName() {
        timesUpgraded++;
        upgraded = true;
        switch (language) {
            case ZHS:
                name = "悖论构造体";
                break;
            case ZHT:
                name = "悖論構造体";
                break;
            case JPN:
                name = "ブラックバレル";
            default:
                break;
        }
    }



    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber;
        if (!mo.isDying && !mo.isDead && mo.currentBlock > 0) {
            this.baseDamage += mo.currentBlock;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
