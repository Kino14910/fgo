package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.DefenseDownPower;
import fgo.util.CardStats;

public class IntoxicatedMeal extends FGOCard {
    public static final String ID = makeID(IntoxicatedMeal.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );
    public IntoxicatedMeal() {
        super(ID, INFO);
        setMagic(2);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            target = CardTarget.ALL_ENEMY;
        }
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber), magicNumber, AbstractGameAction.AttackEffect.POISON));
            addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, magicNumber), magicNumber));
        } else {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, magicNumber), magicNumber));
                addToBot(new ApplyPowerAction(monster, p, new DefenseDownPower(monster, magicNumber), magicNumber));
            }
        }
    }
}
