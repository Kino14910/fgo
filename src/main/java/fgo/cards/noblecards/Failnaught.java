package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class Failnaught extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Failnaught.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Failnaught.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Failnaught_p.png";

    public Failnaught() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(32, 8);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Failnaught();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveAllBlockAction(m, p));
        if (m.hasPower(ArtifactPower.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(m, p, ArtifactPower.POWER_ID));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
    }
}
