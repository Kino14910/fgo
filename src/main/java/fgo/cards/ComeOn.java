package fgo.cards;

import fgo.action.AttackComeOnAction;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.util.CardStats;
import fgo.patches.Enum.FGOCardColor;

public class ComeOn extends FGOCard {
    public static final String ID = makeID(ComeOn.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            0
    );
    public ComeOn() {
        super(ID, INFO);
        setDamage(7, 4);
        this.portraitImg = ImageMaster.loadImage("fgo/images/cards/attack/ComeOn.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)

    @Override
    public AbstractCard makeCopy() {
        return new ComeOn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));
        this.addToBot(new AttackComeOnAction(this, AbstractGameAction.AttackEffect.FIRE));
        //this.addToBot(new ModifyMagicNumAction(this.uuid, this.magicNumber));
    }
}
