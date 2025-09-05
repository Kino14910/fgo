package fgo.cards.fgo;

import static fgo.characters.CustomEnums.Foreigner;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.EnergyRegenPower;
import fgo.powers.NPDamagePower;

public class AbyssLight extends FGOCard {
    public static final String ID = makeID(AbyssLight.class.getSimpleName());
    
    public AbyssLight() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF, CardRarity.RARE);
        setMagic(20, 10);
        setNP(20, 10);
        tags.add(Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }
    

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExpungeVFXAction(m));
        addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, 10, 3)));
        addToBot(new FgoNpAction(np));
        addToBot(new ApplyPowerAction(p, p, new NPDamagePower(magicNumber)));
    }
}

