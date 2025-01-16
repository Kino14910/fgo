package fgo.cards;

import fgo.action.FgoNpAction;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.patches.Enum.AbstractCardEnum;
import fgo.util.CardStats;

public class CharismaOfHope extends FGOCard {
    public static final String ID = makeID(CharismaOfHope.class.getSimpleName());
    private static final CardStats info = new CardStats(
            AbstractCardEnum.Master_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    public CharismaOfHope() {
        super(ID, info);
        setBlock(7, 10);
        setNP(10, 10);
        setCasterBg();

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CharismaOfHope();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new FgoNpAction(this.magicNumber));

//        switch (AbstractDungeon.actNum) {
//            case 1:
//                this.addToBot(new FgoNpAction(10, true));
//                break;
//            case 2:
//                this.addToBot(new FgoNpAction(20, true));
//                this.addToBot(new DrawCardAction(p, 1));
//                break;
//            default:
//                this.addToBot(new FgoNpAction(30, true));
//                this.addToBot(new DrawCardAction(p, 2));
//                break;
//        }
    }

    /*@Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actNum == 2) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        } else if (AbstractDungeon.actNum > 2) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        }

        this.initializeDescription();
    }*/
}
