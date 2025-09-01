package fgo.cards.colorless;

import java.util.Iterator;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.utils.CardStats;

public class GrandOrder extends FGOCard {
    public static final String ID = makeID(GrandOrder.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );
    public GrandOrder() {
        super(ID, INFO);
        setDamage(9999);
        setCostUpgrade(0);
        FleetingField.fleeting.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractMonster> var1 = AbstractDungeon.getMonsters().monsters.iterator();
        do {
            if (!var1.hasNext()) {
                return;
            }

            m = var1.next();
        } while(m.type == AbstractMonster.EnemyType.BOSS);
        this.addToBot(new DamageAllEnemiesAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        // addToBot(new DamageAction(m, new DamageInfo(m, damage)));
    }

}
