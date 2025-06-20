package fgo.cards.noblecards;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class Shishifunjin extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Shishifunjin.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Shishifunjin.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Shishifunjin_p.png";

    public Shishifunjin() {
        super(ID,CardType.POWER, CardTarget.ALL_ENEMY);
        setMagic(40, 10);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(StunMonsterPower.POWER_ID)) {
                addToBot(new LoseHPAction(mo, p, (int)Math.floor(mo.maxHealth / 100.0 * magicNumber), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }
}
