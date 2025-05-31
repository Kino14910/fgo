package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.patches.Enum.CardTagsEnum;
import fgo.powers.WatersidePower;

public class Summer extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Summer.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Summer.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Summer_p.png";

    public Summer() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setNP(60, 20);
        setExhaust();
        this.tags.add(CardTagsEnum.Noble_Phantasm);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WatersidePower(p)));
        addToBot(new FgoNpAction(np));
    }
}
