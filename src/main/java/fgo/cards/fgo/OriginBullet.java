package fgo.cards.fgo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.action.IgnoresInvincibilityAction;
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
        setDamage(8, 4);
        setMagic(3, 3);
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int BulletAmt = 0;
        for (AbstractPower ignored : m.powers) {
            ++BulletAmt;
        }

        int realBaseDamage = baseDamage;
        baseDamage += BulletAmt * magicNumber;
        super.calculateCardDamage(m);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IgnoresInvincibilityAction(m, baseDamage));
    }
}
