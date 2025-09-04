package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;

import fgo.FGOMod;
import fgo.action.WaitFgoAction;
import fgo.action.lor.ChangeSceneEffect;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.UnlimitedPower;
import fgo.utils.Sounds;

public class Unlimited extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Unlimited.class.getSimpleName());
    public Unlimited() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(1, 1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
        addToBot(new WaitFgoAction(1.0F));
        addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage(FGOMod.vfxPath("UnlimitedBg.png")))));
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.silenceBGMInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly(Sounds.UBW_Music);
        addToBot(new ApplyPowerAction(p, p, new UnlimitedPower(p, magicNumber)));
    }
}
