package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.GloriousStrikePower;
public class GloriousStrike extends FGOCard {
    public static final String ID = makeID(GloriousStrike.class.getSimpleName());
    public GloriousStrike() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY, CardRarity.UNCOMMON);
        setDamage(8, 4);
        setMagic(2);
        setExhaust();
        tags.add(CardTags.STRIKE);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        addToBot(new ApplyPowerAction(p, p, new GloriousStrikePower(p)));
    }
}


