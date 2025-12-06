package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;

public class BlessedRegenerate extends FGOCard {
    public static final String ID = makeID(BlessedRegenerate.class.getSimpleName());

    public BlessedRegenerate() {
        super(ID, -2, CardType.POWER, CardTarget.NONE, CardRarity.SPECIAL, CardColor.COLORLESS);
        setMagic(2, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GainEnergyAction(1));
        addToBot(new DrawCardAction(p, magicNumber));
    }
}
