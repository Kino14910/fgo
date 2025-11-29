package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import fgo.cards.FGOCard;

public class PoisonousDagger extends FGOCard {
    public static final String ID = makeID(PoisonousDagger.class.getSimpleName());

    public PoisonousDagger() {
        super(ID, 0, CardType.SKILL, CardTarget.ENEMY, CardRarity.SPECIAL, CardColor.COLORLESS);
        setDamage(2);
        setMagic(2, 2);
        this.exhaust = true;
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber));
    }
}
