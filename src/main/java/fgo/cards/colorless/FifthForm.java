package fgo.cards.colorless;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.FifthFormPower;
public class FifthForm extends FGOCard {
    public static final String ID = makeID(FifthForm.class.getSimpleName());
    public FifthForm() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.RARE, CardColor.COLORLESS);
        setMagic(50, -15);
        this.portraitImg = ImageMaster.loadImage("fgo/images/cards/skill/FifthForm.png");
        setExhaust();

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.addToBot(new ApplyPowerAction(p, p, new FifthFormPower(p, 1, true), 1));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new FifthFormPower(p, 1, false), 1));
        }
    }
}


