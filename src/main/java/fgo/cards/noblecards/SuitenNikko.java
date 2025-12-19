package fgo.cards.noblecards;

import static fgo.utils.ModHelper.addToBotAbstract;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;

public class SuitenNikko extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SuitenNikko.class.getSimpleName());

    public SuitenNikko() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF, 1);
        setMagic(30, 5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBotAbstract(() -> {
            for (AbstractCard c : p.hand.group) {
                c.setCostForTurn(c.costForTurn - 1);
            }
        });
        addToBot(new FgoNpAction(magicNumber));
    }
}

