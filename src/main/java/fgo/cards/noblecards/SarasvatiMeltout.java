package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.SarasvatiMeltoutAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.patches.Enum.CardTagsEnum;

public class SarasvatiMeltout extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SarasvatiMeltout.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/SarasvatiMeltout.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/SarasvatiMeltout_p.png";

    public SarasvatiMeltout() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(40, 8);
        setExhaust();
        setSelfRetain();
        this.tags.add(CardTagsEnum.Noble_Phantasm);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SarasvatiMeltoutAction(m));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
    }
}
