package fgo.cards.tempCards;

import static fgo.FGOMod.cardPath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;

import fgo.cards.FGOCard;

public class FgoInsight extends FGOCard {
    public static final String ID = makeID(FgoInsight.class.getSimpleName());

    public FgoInsight() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.SPECIAL, CardColor.COLORLESS);
        setMagic(2, 1);
        setExhaust();
        setSelfRetain();
        this.portraitImg = ImageMaster.loadImage(cardPath("skill/FgoInsight"));

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new LightBulbEffect(p.hb)));
        } else {
            this.addToBot(new VFXAction(new LightBulbEffect(p.hb), 0.2F));
        }

        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }
}
