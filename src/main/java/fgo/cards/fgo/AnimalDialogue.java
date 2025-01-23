package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.characters.Master;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class AnimalDialogue extends FGOCard {
    public static final String ID = makeID(AnimalDialogue.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public AnimalDialogue() {
        super(ID, INFO);
        setMagic(20, -5);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Master.fgoNp >= magicNumber) {
            addToBot(new GainEnergyAction(Master.fgoNp / magicNumber));
            addToBot(new FgoNpAction(-Master.fgoNp));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (Master.fgoNp >= magicNumber) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
