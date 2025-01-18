package fgo.cards.noblecards;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.BurnDamagePower;

public class ParadisChateaudIf extends AbsNoblePhantasmCard {
    public static final String ID = makeID(ParadisChateaudIf.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/ParadisChateaudIf.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/ParadisChateaudIf_p.png";

    public ParadisChateaudIf() {
        super(ID,CardType.ATTACK, CardTarget.ENEMY);
        setDamage(0, 1);
        setMagic(5);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ParadisChateaudIf();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(m, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
        if (!m.hasPower(StunMonsterPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m)));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        //this.baseDamage += this.magicNumber;
        if (mo.hasPower(BurnDamagePower.POWER_ID)) {
            int BurnAmt = mo.getPower(BurnDamagePower.POWER_ID).amount;
            this.baseDamage += BurnAmt;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void applyPowers() {
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        super.applyPowers();
        this.initializeDescription();
    }
}
