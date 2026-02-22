package fgo.powers;

import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.CustomEnums.FGOCardColor;

public class NPDamagePower extends BasePower {
    public static final String POWER_ID = makeID(NPDamagePower.class.getSimpleName());

    public NPDamagePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, AbstractDungeon.player, Math.min(amount, 999));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.color == FGOCardColor.NOBLE_PHANTASM) {
            return atDamageGive(damage, type);
        }

        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * (100 + amount) / 100 : damage;
    }

    
}
