package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

import fgo.cards.FGOCard;
import fgo.cards.tempCards.SoulOfWaterChannels;
import fgo.powers.WatersidePower;
public class JourneyGuidance extends FGOCard {
    public static final String ID = makeID(JourneyGuidance.class.getSimpleName());
    public JourneyGuidance() {
        super(ID, 2, CardType.ATTACK, CardTarget.ENEMY, CardRarity.COMMON);
        setDamage(14, 4);
        cardsToPreview = new SoulOfWaterChannels();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
        } else {
            addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
        }
        for(i = 0; i < 5; ++i) {
            addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        }

        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (p.hasPower(WatersidePower.POWER_ID)) {
            addToBot(new GainEnergyAction(2));
        }
        addToBot(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.player.hasPower(WatersidePower.POWER_ID)
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
                : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}


