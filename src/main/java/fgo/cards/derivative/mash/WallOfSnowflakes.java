package fgo.cards.derivative.mash;

import static fgo.FGOMod.imagePath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.utils.CardStats;

public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG2 = imagePath("cards/skill/MashPaladin.png");
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO_DERIVATIVE,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );
    public WallOfSnowflakes() {
        super(ID, INFO);
        setBlock(15, 5);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
}
