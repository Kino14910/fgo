package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class DreamUponTheStars extends FGOCard {
    public static final String ID = makeID(DreamUponTheStars.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    public DreamUponTheStars() {
        super(ID, INFO);
        setDamage(9, 12);
        setNP(10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new FgoNpAction(np));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DreamUponTheStars();
    }

}
