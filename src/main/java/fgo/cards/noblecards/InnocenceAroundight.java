package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.cards.tempCards.RayHorizon;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.NPRatePower;

public class InnocenceAroundight extends AbsNoblePhantasmCard {
    public static final String ID = makeID(InnocenceAroundight.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/InnocenceAroundight.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/InnocenceAroundight_p.png";

    public InnocenceAroundight() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(32, 8);
        this.isMultiDamage = true;
        setMagic(3);
        setExhaust();
        this.cardsToPreview = new RayHorizon();
        this.cardsToPreview.upgrade();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 1, true, true, false));
    }
}
