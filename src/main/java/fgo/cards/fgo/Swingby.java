package fgo.cards.fgo;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.StarGainPower;
import fgo.util.CardStats;

public class Swingby extends FGOCard {
    public static final String ID = makeID(Swingby.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    public Swingby() {
        super(ID, INFO);
        setMagic(3, 1);
        setEthereal();
        tags.add(CardTagsEnum.Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Swingby();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int theSize = AbstractDungeon.player.hand.size();
        addToTop(new DiscardAction(p, p, theSize, false));
        if (theSize >= 2) {
            addToBot(new GainBlockAction(p, p, magicNumber*(theSize-1)));
            addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, magicNumber*(theSize-1)), magicNumber*(theSize-1)));
        }
    }
}
