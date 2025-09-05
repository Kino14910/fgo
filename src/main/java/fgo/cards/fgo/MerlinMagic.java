package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
public class MerlinMagic extends FGOCard {
    public static final String ID = makeID(MerlinMagic.class.getSimpleName());
    public MerlinMagic() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.COMMON);
        setBlock(7, 2);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/skill/MerlinMagic.png");
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped()) {
                addToBot(new GainBlockAction(p, p, block));
            }
        }
    }
}


