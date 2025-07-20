package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.ReflectionHacks;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.ReducePercentDamagePower;
import fgo.util.CardStats;

public class HolyShroud extends FGOCard {
    public static final String ID = makeID(HolyShroud.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public HolyShroud() {
        super(ID, INFO);
        setMagic(25, 5);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int sum = 0;
        
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            int dmg = (int)ReflectionHacks.getPrivate(monster, AbstractMonster.class, "intentDmg");
            int actualDmg = (boolean)ReflectionHacks.getPrivate(monster, AbstractMonster.class, "isMultiDmg")
                    ? dmg * (int)ReflectionHacks.getPrivate(monster, AbstractMonster.class, "intentMultiAmt")
                    : dmg;
            sum += actualDmg;
        }

        return sum >= 30;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber), magicNumber));
    }
}
