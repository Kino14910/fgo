package fgo.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

import java.util.ArrayList;
import java.util.Iterator;

public class GrandOrder extends FGOCard {
    public static final String ID = makeID(GrandOrder.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );
    public GrandOrder() {
        super(ID, INFO);
        setDamage(9999);
        this.isMultiDamage = true;
        setExhaust();
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new GrandOrder();
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
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        ArrayList<AbstractCard> removeList = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c instanceof GrandOrder) {
                removeList.add(c);
                break;
            }
        }
        AbstractDungeon.actionManager.cardsPlayedThisCombat.removeIf(c -> c instanceof GrandOrder);
        AbstractDungeon.player.masterDeck.group.removeIf(removeList::contains);
    }

}
