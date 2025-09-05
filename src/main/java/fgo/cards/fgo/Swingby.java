package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.StarPower;
public class Swingby extends FGOCard {
    public static final String ID = makeID(Swingby.class.getSimpleName());
    public Swingby() {
        super(ID, 2, CardType.SKILL, CardTarget.SELF, CardRarity.RARE);
        setMagic(3, 1);
        setEthereal();

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int theSize = AbstractDungeon.player.hand.size();
        addToTop(new DiscardAction(p, p, theSize, false));
        if (theSize >= 2) {
            addToBot(new GainBlockAction(p, p, magicNumber*(theSize-1)));
            addToBot(new ApplyPowerAction(p, p, new StarPower(p, magicNumber*(theSize-1)), magicNumber*(theSize-1)));
        }
    }
}


