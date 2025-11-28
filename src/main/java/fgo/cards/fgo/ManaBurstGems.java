package fgo.cards.fgo;

import static fgo.FGOMod.cardPath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import fgo.cards.FGOCard;
import fgo.powers.ManaBurstGemsPower;
public class ManaBurstGems extends FGOCard {
    public static final String ID = makeID(ManaBurstGems.class.getSimpleName());
    public ManaBurstGems() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setMagic(2, 1);
        portraitImg = ImageMaster.loadImage(cardPath("skill/ManaBurstGems"));
        
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ManaBurstGemsPower(p, magicNumber)));
        addToBot(new VFXAction(p, new VerticalAuraEffect(Color.FIREBRICK, p.hb.cX, p.hb.cY), 0.0F));
        addToBot(new MakeTempCardInDiscardAction(makeStatEquivalentCopy(), 1));
    }
}


