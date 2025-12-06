package fgo.cards.fgo;

import static fgo.characters.CustomEnums.Foreigner;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.CursePower;
import fgo.powers.GutsPower;
public class VoidSpaceFineArts extends FGOCard {
    public static final String ID = makeID(VoidSpaceFineArts.class.getSimpleName());
    public VoidSpaceFineArts() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON);
        setMagic(5, 5);
        tags.add(CardTags.HEALING);
        tags.add(Foreigner);

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
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GutsPower(p, 10)));
        for(int i = 0; i < 3; ++i) {
            addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1)));
        }
        
        if (p.hasPower(CursePower.POWER_ID)) {
            int CurseAmt = p.getPower(CursePower.POWER_ID).amount;
            addToBot(new FgoNpAction(magicNumber * CurseAmt));
        }
    }
}


