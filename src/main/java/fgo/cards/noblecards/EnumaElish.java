package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;

public class EnumaElish
extends AbsNoblePhantasmCard {
    public static final String ID = makeID(EnumaElish.class.getSimpleName());

    public EnumaElish() {
        super(ID, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL_ENEMY);
        setDamage(32, 8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}

