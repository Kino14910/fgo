package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class BeautifulJourney extends AbsNoblePhantasmCard {
    public static final String ID = makeID(BeautifulJourney.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/BeautifulJourney.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/BeautifulJourney_p.png";

    public BeautifulJourney() {
        super(ID, CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(24, 6);
        setNP(20);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                addToBot(new FgoNpAction(np));
            }
        }
    }
}
