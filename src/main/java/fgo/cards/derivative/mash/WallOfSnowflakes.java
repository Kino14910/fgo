package fgo.cards.derivative.mash;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;

public class WallOfSnowflakes extends FGOCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());

    public WallOfSnowflakes() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.SPECIAL, FGOCardColor.FGO_DERIVATIVE);
        setBlock(15, 5);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.GOLD);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
}
