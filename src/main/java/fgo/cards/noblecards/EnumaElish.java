package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.utils.TextureLoader;

public class EnumaElish
extends AbsNoblePhantasmCard {
    public static final String ID = makeID(EnumaElish.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/EnumaElish.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/EnumaElish_p.png";

    public EnumaElish() {
        super(ID, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL_ENEMY);
        setDamage(32, 8);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}

