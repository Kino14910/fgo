package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.powers.BurnDamagePower;
import fgo.util.CardStats;

public class FlamesofApplause extends FGOCard {
    public static final String ID = makeID(FlamesofApplause.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );
    public FlamesofApplause() {
        super(ID, INFO);
        setDamage(3, 1);
        setMagic(1);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), true));
        addToBot(new ApplyPowerAction(m, p, new BurnDamagePower(m, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.FIRE));
    }
}
