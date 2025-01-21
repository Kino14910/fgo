package fgo.cards.fgo.Ignore;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class StarPioneer extends FGOCard {
    public static final String ID = makeID(StarPioneer.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    public StarPioneer() {
        super(ID, INFO);
        setMagic(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new StarPioneer();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }

        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber)));
        addToBot(new ArmamentsAction(upgraded));
    }
}
