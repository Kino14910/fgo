package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;

public class SecondLife extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SecondLife.class.getSimpleName());

    public SecondLife() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setNP(25, 25);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhumeAction(true));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.hasPower("Minion")) continue;
            addToBot(new InstantKillAction(monster));
            addToBot(new FgoNpAction(np));
        }
    }
}
