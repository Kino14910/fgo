package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.powers.CursePower;
import fgo.powers.StarPower;
import fgo.utils.CardStats;

public class SoulOfWaterChannels extends FGOCard {
    public static final String ID = makeID(SoulOfWaterChannels.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );
    public SoulOfWaterChannels() {
        super(ID, INFO);
        setStar(10, 5);
        setExhaust();
        setSelfRetain();
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));

        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }

        this.addToBot(new ApplyPowerAction(p, p, new StarPower(p, this.star)));
    }
}
