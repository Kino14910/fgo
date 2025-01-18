package fgo.cards.noblecards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.EnferChateaudIfAction;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class EnferChateaudIf extends AbsNoblePhantasmCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("EnferChateaudIf");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "fgo/images/cards/noble/EnferChateaudIf.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/EnferChateaudIf_p.png";
    public static final String ID = "EnferChateaudIf";
    public EnferChateaudIf() {
        super(ID, NAME, IMG_PATH, DESCRIPTION, CardType.POWER, CardTarget.SELF);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnferChateaudIf();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EnferChateaudIfAction());
    }
}
