package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.StarPower;

public class HollowHeartAlbion extends AbsNoblePhantasmCard {
    public static final String ID = makeID(HollowHeartAlbion.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/HollowHeartAlbion.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/HollowHeartAlbion_p.png";

    public HollowHeartAlbion() {
        super(ID,AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL_ENEMY);
        setDamage(27, 8);
        setStar(10);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new HollowHeartAlbion();
    }

}
