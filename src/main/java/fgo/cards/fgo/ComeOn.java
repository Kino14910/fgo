package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.AttackComeOnAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class ComeOn extends FGOCard {
    public static final String ID = makeID(ComeOn.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    public ComeOn() {
        super(ID, INFO);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/skill/ComeOn.png");
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
        setExhaust();
    }

    @Override
    public AbstractCard makeCopy() {
        return new ComeOn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));
        addToBot(new AttackComeOnAction(this, AbstractGameAction.AttackEffect.FIRE));
        //addToBot(new ModifyMagicNumAction(uuid, magicNumber));
    }
}
