package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.utils.CardStats;

public class CharismaofAdversity extends FGOCard {
    public static final String ID = makeID(CharismaofAdversity.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );
    public CharismaofAdversity() {
        super(ID, INFO);
        setDamage(6, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }

        int lostHealthPercentage = p.maxHealth - p.currentHealth;
        int extraHits = lostHealthPercentage / 12;
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (extraHits >= 1) {
            for (int i = 0; i < extraHits; ++i) {
                addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
    }

    @Override
    public void applyPowers() {
        int lostHealthPercentage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        int extraHits = lostHealthPercentage / 12;
        if (extraHits >= 1) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + extraHits + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth >= 12 ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}
