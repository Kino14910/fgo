package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class SecondLife extends AbsNoblePhantasmCard {
    public static final String ID = makeID(SecondLife.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/SecondLife.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/SecondLife_p.png";

    public SecondLife() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setNP(25, 25);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhumeAction(true));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.hasPower("Minion")) continue;
            addToBot(new InstantKillAction(monster));
            addToBot(new FgoNpAction(np));
        }
    }
}
