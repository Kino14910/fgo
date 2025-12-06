package fgo.cards.colorless;

import static fgo.FGOMod.cardPath;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.ViyViyViyAction;
import fgo.cards.FGOCard;
public class Shvibzik extends FGOCard {
    public static final String ID = makeID(Shvibzik.class.getSimpleName());
    public Shvibzik() {
        super(ID, 2, CardType.ATTACK, CardTarget.ENEMY, CardRarity.SPECIAL, CardColor.COLORLESS);
        setDamage(20, 5);
        setExhaust();
        portraitImg = ImageMaster.loadImage(cardPath("attack/Shvibzik"));

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ViyViyViyAction());
    }
}


