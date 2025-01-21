package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class CharismaOfTheJade extends FGOCard {
    public static final String ID = makeID(CharismaOfTheJade.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3
    );
    public CharismaOfTheJade() {
        super(ID, INFO);
        setDamage(9, 3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CharismaOfTheJade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            addToBot(new WaitAction(0.8F));
            for (int i = 0; i < 3; i++) {
                addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
    }
}
