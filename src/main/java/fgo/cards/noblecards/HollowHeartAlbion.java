package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.powers.StarGainPower;

public class HollowHeartAlbion extends AbsNoblePhantasmCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("HollowHeartAlbion");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/cards_noble/HollowHeartAlbion.png";
    public static final String IMG_PATH_P = "fgo/images/cards/cards_noble/HollowHeartAlbion_p.png";
    public static final String ID = "HollowHeartAlbion";
    public HollowHeartAlbion() {
        super(ID, NAME, IMG_PATH, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL_ENEMY);
        this.baseDamage = 27;
        this.isMultiDamage = true;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HollowHeartAlbion();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, 10), 10));
    }
}
