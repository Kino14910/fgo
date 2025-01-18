package fgo.cards.fgo;

import fgo.action.FgoNpAction;
import basemod.abstracts.CustomCard;
import fgo.characters.master;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.BaseCard;
import fgo.cards.FGOCard;
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
    public AbstractCard makeCopy() {
        return new AnimalDialogue();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (master.fgoNp >= magicNumber) {
            addToBot(new GainEnergyAction(master.fgoNp / magicNumber));
            addToBot(new FgoNpAction(-master.fgoNp));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (master.fgoNp >= magicNumber) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
