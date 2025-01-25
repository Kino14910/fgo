package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class OriginBullet extends FGOCard {
    public static final String ID = makeID(OriginBullet.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public OriginBullet() {
        super(ID, INFO);
        setDamage(8);
        setMagic(3, 3);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int BulletAmt = 0;
        for (AbstractPower ignored : mo.powers) {
            ++BulletAmt;
        }

        int realBaseDamage = baseDamage;
        baseDamage += BulletAmt * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
//        int theSize = AbstractDungeon.player.hand.size();
//        addToTop(new DiscardAction(p, p, theSize, false));
    }
}
