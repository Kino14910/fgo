package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.BaneAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class YewBow extends AbsNoblePhantasmCard {
    public static final String ID = makeID(YewBow.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/YewBow.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/YewBow_p.png";
    public YewBow() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(25, 5);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new BaneAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
    }
}
