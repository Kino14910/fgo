package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.ManaBurstGemsPower;
import fgo.util.CardStats;

public class ManaBurstGems extends FGOCard {
    public static final String ID = makeID(ManaBurstGems.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public ManaBurstGems() {
        super(ID, INFO);
        setMagic(2, 1);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/skill/ManaBurstGems.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ManaBurstGemsPower(p, magicNumber)));
        addToBot(new VFXAction(p, new VerticalAuraEffect(Color.FIREBRICK, p.hb.cX, p.hb.cY), 0.0F));
        addToBot(new MakeTempCardInDiscardAction(makeStatEquivalentCopy(), 1));
    }
}
