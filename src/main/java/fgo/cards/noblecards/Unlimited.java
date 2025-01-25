package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
import fgo.action.WaitFgoAction;
import fgo.action.lor.ChangeSceneEffect;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.UnlimitedPower;

public class Unlimited extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Unlimited.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Unlimited.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Unlimited_p.png";
    public Unlimited() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(1, 1);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
        this.addToBot(new WaitFgoAction(1.0F));
        this.addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage("fgo/images/vfx_master/UnlimitedBg.png"))));
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.silenceBGMInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly("UBW_Extended.mp3");
        for(int i = 0; i < this.magicNumber; ++i) {
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            c.setCostForTurn(0);
            this.addToBot(new MakeTempCardInHandAction(c, 1, true));
        }
        this.addToBot(new ApplyPowerAction(p, p, new UnlimitedPower(p, this.magicNumber), this.magicNumber));
    }
}
