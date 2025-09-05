package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.GutsPower;

public class CharismaOfConflict extends FGOCard {
    public static final String ID = makeID(CharismaOfConflict.class.getSimpleName());

    public CharismaOfConflict() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY, CardRarity.UNCOMMON);
        setDamage(7, 3);
        
        portraitImg = ImageMaster.loadImage("fgo/images/cards/attack/WindsweptSlash.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, damageType, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (p.hasPower(GutsPower.POWER_ID)) {
            addToBot(new DamageAllEnemiesAction(p, damage, damageType, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}

