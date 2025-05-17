package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.PutOnFakeFacePower;
import fgo.util.CardStats;

public class PutOnFakeFace extends FGOCard {
    public static final String ID = makeID(PutOnFakeFace.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public static final String IMG = "fgo/images/cards/skill/AtTheWell.png";
    public PutOnFakeFace() {
        super(ID, INFO, IMG);
        setNP(20, 10);
        setMagic(3, 2);
    }
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PutOnFakeFacePower(p, np, magicNumber, 1)));
    }
}
