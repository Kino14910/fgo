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

import java.util.ArrayList;
import java.util.Iterator;

public class GrandOrder extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("GrandOrder");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/GrandOrder.png";
    private static final int COST = 1;
    public static final String ID = "GrandOrder";
    public GrandOrder() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 99999;
        this.isMultiDamage = true;
        this.exhaust = true;
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
