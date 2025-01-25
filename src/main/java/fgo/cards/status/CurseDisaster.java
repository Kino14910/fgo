package fgo.cards.status;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CursePower;
import fgo.util.CardStats;

public class CurseDisaster extends FGOCard {
    public static final String ID = makeID(CurseDisaster.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    public CurseDisaster() {
        super(ID, INFO);
        setMagic(1);
        setEthereal();
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));

        this.addToBot(
                new VFXAction(
                        p,
                        new ShockWaveEffect(
                                p.hb.cX,
                                p.hb.cY,
                                Settings.GREEN_TEXT_COLOR,
                                ShockWaveEffect.ShockWaveType.CHAOTIC
                        ),
                        Settings.FAST_MODE ? 0.3F :1.5F
                )
        );

        this.addToBot(
                new ApplyPowerAction(p, p, new CursePower(p, magicNumber))
        );
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber,
                monster -> new CursePower(monster, magicNumber)));
    }
}
