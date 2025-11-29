package fgo.cards.fgo;

import static fgo.characters.CustomEnums.Foreigner;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.TerrorPower;
import fgo.powers.VSTerrorDamageUpPower;

public class WitchOfSalem extends FGOCard {
    public static final String ID = makeID(WitchOfSalem.class.getSimpleName());

    public WitchOfSalem() {
        super(ID, 1, CardType.SKILL, CardTarget.ALL_ENEMY, CardRarity.RARE);
        setMagic(30, 20);
        setMagic2(50, 50);

        setNP(20);
        tags.add(Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                if (mo.isDead || mo.isDying) continue;
                addToBot(new ApplyPowerAction(mo, p, new TerrorPower(mo, 3, magicNumber)));
            }
        
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, 3, false)));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 3, false)));
        addToBot(new ApplyPowerAction(p, p, new VSTerrorDamageUpPower(p, magicNumber2)));
        addToBot(new FgoNpAction(np));
    }
}
