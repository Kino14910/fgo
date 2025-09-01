package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.NPOverchargePower;

public class RoadlessCamelot extends AbsNoblePhantasmCard {
    public static final String ID = makeID(RoadlessCamelot.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/RoadlessCamelot.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/RoadlessCamelot_p.png";

    public RoadlessCamelot() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(8, 2);
        setMagic(1, 1);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < 3; ++i) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ExpungeVFXAction(mo));
            }
            addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        addToBot(new ApplyPowerAction(p, p, new NPOverchargePower(p, magicNumber)));
    }
}
