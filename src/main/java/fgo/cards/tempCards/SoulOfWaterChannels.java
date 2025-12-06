package fgo.cards.tempCards;

import static fgo.characters.CustomEnums.FGO_Foreigner;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import fgo.cards.FGOCard;
import fgo.powers.CursePower;
import fgo.powers.StarPower;

public class SoulOfWaterChannels extends FGOCard {
    public static final String ID = makeID(SoulOfWaterChannels.class.getSimpleName());

    public SoulOfWaterChannels() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF, CardRarity.SPECIAL, CardColor.COLORLESS);
        setStar(10, 5);
        setExhaust();
        setSelfRetain();
        this.tags.add(FGO_Foreigner);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));

        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }

        addToBot(new ApplyPowerAction(p, p, new StarPower(p, this.star)));
    }
}
