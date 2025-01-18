package fgo.cards.fgo;

import fgo.action.MagicBulletChargingAction;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.MagicBulletPower;
import fgo.util.CardStats;

public class MagicBulletCharging extends FGOCard {
    public static final String ID = makeID(MagicBulletCharging.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            -1
    );
    public MagicBulletCharging() {
        super(ID, INFO);
        setDamage(15, 5);
        isMultiDamage = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicBulletCharging();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(MagicBulletPower.POWER_ID)) {
            addToBot(new VFXAction(new BorderFlashEffect(new Color(0.8F, 0.5F, 1.0F, 1.0F))));
            addToBot(new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
            addToBot(new MagicBulletChargingAction(p, freeToPlayOnce, energyOnUse));
        } else {
            if (p.hasPower(MagicBulletPower.POWER_ID)) {
                int MagicBullet = p.getPower(MagicBulletPower.POWER_ID).amount;
                for (int i = 0; i < MagicBullet; ++i) {
                    addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                addToBot(new RemoveSpecificPowerAction(p, p, MagicBulletPower.POWER_ID));
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(MagicBulletPower.POWER_ID)) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(MagicBulletPower.POWER_ID)) {
            setMagic(AbstractDungeon.player.getPower(MagicBulletPower.POWER_ID).amount);
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        }
        super.applyPowers();
        initializeDescription();
    }
}
