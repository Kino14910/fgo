package fgo.cards.fgo;

import fgo.action.VoidSpaceFineArtsAction;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CursePower;
import fgo.powers.GutsPower;
import fgo.util.CardStats;

public class VoidSpaceFineArts extends FGOCard {
    public static final String ID = makeID(VoidSpaceFineArts.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public VoidSpaceFineArts() {
        super(ID, INFO);
        setMagic(5, 5);
        setExhaust();
        tags.add(CardTags.HEALING);
        tags.add(CardTagsEnum.Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new VoidSpaceFineArts();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GutsPower(p, 10, 1), 10));
        for(int i = 0; i < 3; ++i) {
            addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));
        }
        addToBot(new VoidSpaceFineArtsAction(magicNumber));
    }
}
