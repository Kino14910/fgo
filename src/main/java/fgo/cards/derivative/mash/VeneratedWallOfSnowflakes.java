package fgo.cards.derivative.mash;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.ReducePercentDamagePower;
import fgo.utils.CardStats;

public class VeneratedWallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(VeneratedWallOfSnowflakes.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );

    public VeneratedWallOfSnowflakes() {
        super(ID, INFO);
        setBlock(10, 10);
        setMagic(20, 10);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public float getTitleFontSize() {
        return 24.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber), magicNumber));
    }
}
