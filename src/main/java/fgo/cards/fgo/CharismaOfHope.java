package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class CharismaOfHope extends FGOCard {
    public static final String ID = makeID(CharismaOfHope.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    public CharismaOfHope() {
        super(ID, INFO);
        setBlock(7, 3);
        setNP(10, 10);
        setCasterBackground();

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CharismaOfHope();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new FgoNpAction(np));

//        switch (AbstractDungeon.actNum) {
//            case 1:
//                addToBot(new FgoNpAction(10, true));
//                break;
//            case 2:
//                addToBot(new FgoNpAction(20, true));
//                addToBot(new DrawCardAction(p, 1));
//                break;
//            default:
//                addToBot(new FgoNpAction(30, true));
//                addToBot(new DrawCardAction(p, 2));
//                break;
//        }
    }

    /*@Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actNum == 2) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        } else if (AbstractDungeon.actNum > 2) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        }

        initializeDescription();
    }*/
}
