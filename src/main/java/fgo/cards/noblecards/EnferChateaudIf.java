package fgo.cards.noblecards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.EnferChateaudIfAction;
import fgo.cards.AbsNoblePhantasmCard;

public class EnferChateaudIf extends AbsNoblePhantasmCard {
    public static final String ID = makeID(EnferChateaudIf.class.getSimpleName());

    public EnferChateaudIf() {
        super(ID,CardType.POWER, CardTarget.SELF, 1);
    }
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EnferChateaudIfAction());
    }
}
