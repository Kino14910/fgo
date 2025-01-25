package fgo.cards.colorless;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.CurtainoftheNightAction;
import fgo.cards.FGOCard;
import fgo.util.CardStats;

public class ProofAndRebuttal extends FGOCard {
    public static final String ID = makeID(ProofAndRebuttal.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );
    public ProofAndRebuttal() {
        super(ID, INFO);
        setExhaust();
        this.portraitImg = ImageMaster.loadImage("fgo/images/cards/skill/ProofAndRebuttal.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, 1));
        if (!AbstractDungeon.player.hand.isEmpty()) {
            this.addToBot(new CurtainoftheNightAction());
        }
    }
}
