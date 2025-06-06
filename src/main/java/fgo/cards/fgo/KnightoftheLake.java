package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.StarPower;
import fgo.util.CardStats;

public class KnightoftheLake extends FGOCard {
    public static final String ID = makeID(KnightoftheLake.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public KnightoftheLake() {
        super(ID, INFO);
        setMagic(50, 50);
        setStar(10);
        setExhaust();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        float duration = Settings.FAST_MODE ?0.1F :0.5F;
        addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), duration));

        if (!p.hasPower(CriticalDamageUpPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, magicNumber)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.player.hasPower(CriticalDamageUpPower.POWER_ID)
                ? AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
                : AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }
}
