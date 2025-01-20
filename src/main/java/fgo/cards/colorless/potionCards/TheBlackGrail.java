package fgo.cards.colorless.potionCards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.BaseCard;
import fgo.cards.FGOCard;
import fgo.cards.colorless.CrimsonSlash;
import fgo.powers.NPDamagePower;
import fgo.util.CardStats;

import static fgo.FGOMod.makeID;

public class TheBlackGrail extends FGOCard {
    public static final String ID = makeID(TheBlackGrail.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public TheBlackGrail() {
        super(ID, INFO);
        setMagic(60, 20);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheBlackGrail();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NPDamagePower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
