package fgo.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import fgo.patches.Enum.AbstractCardEnum;
import fgo.util.CardStats;

public class DreamUponTheStars extends BaseCard {
    public static final String ID = makeID(DreamUponTheStars.class.getSimpleName());
    private static final CardStats info = new CardStats(
            AbstractCardEnum.Master_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            2
    );

    public DreamUponTheStars() {
        super(ID, info);
        setDamage(12);
        setCustomVar("energy", 1, 1);
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, customVar("energy")), customVar("energy")));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DreamUponTheStars();
    }

}
