package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.ItsInevitablePower;
import fgo.utils.Sounds;

public class ItsInevitable extends FGOCard {
    public static final String ID = makeID(ItsInevitable.class.getSimpleName());
    public ItsInevitable() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY, CardRarity.COMMON);
        setDamage(5, 1);
        setMagic(2, 1);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/attack/ItsInevitable.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(Sounds.gun));
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p, p, new ItsInevitablePower(p, damage + magicNumber, magicNumber)));
        //addToBot(new ApplyPowerAction(p, p, new BurnDamagePower(p, magicNumber)));
    }
}


