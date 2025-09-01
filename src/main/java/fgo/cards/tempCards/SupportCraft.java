package fgo.cards.tempCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FateMagineerCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;

public class SupportCraft extends FateMagineerCard {
    public static final String ID = makeID(SupportCraft.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/SupportCraft.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/SupportCraft_p.png";
    public SupportCraft() {
        super(ID, -2, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardColor.COLORLESS);
        setNP(50);
        setExhaust();

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(np));
    }
}
