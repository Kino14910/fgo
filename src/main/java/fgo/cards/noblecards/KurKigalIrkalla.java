package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.BlessingOfKurPower;

public class KurKigalIrkalla extends AbsNoblePhantasmCard {
    public static final String ID = makeID(KurKigalIrkalla.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/KurKigalIrkalla.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/KurKigalIrkalla_p.png";

    public KurKigalIrkalla() {
        super(ID,CardType.ATTACK, CardTarget.ALL_ENEMY);
        setDamage(26, 8);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ExpungeVFXAction(mo));
            if (!mo.hasPower(FlightPower.POWER_ID)) {
                addToBot(new LoseHPAction(mo, p, mo.maxHealth / 10));
            }
        }
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(BlessingOfKurPower.POWER_ID)) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(BlessingOfKurPower.POWER_ID)) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
