package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class LieLikeVortigern extends AbsNoblePhantasmCard {
    public static final String ID = makeID(LieLikeVortigern.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/LieLikeVortigern.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/LieLikeVortigern_p.png";
//    public static final String ID = "LieLikeVortigern";
    public LieLikeVortigern() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(25, 7);
        setMagic(1);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -2)));
            addToBot(new ApplyPowerAction(mo, p, new IntangiblePlayerPower(mo, magicNumber)));
        }

        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber)));
    }
}
