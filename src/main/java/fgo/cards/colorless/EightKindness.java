package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import fgo.cards.FGOCard;
import fgo.utils.CardStats;

public class EightKindness extends FGOCard {
    public static final String ID = makeID(EightKindness.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public EightKindness() {
        super(ID, INFO);
        setMagic(2);
        setExhaust();
    }
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    public void gainPower(AbstractPlayer p, AbstractPower powerToApply){
        this.addToBot(new ApplyPowerAction(p, p,  powerToApply));

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(p, new StrengthPower(p, this.magicNumber));
        gainPower(p, new DexterityPower(p, this.magicNumber));
        gainPower(p, new PlatedArmorPower(p, this.magicNumber));
        gainPower(p, new RegenPower(p, this.magicNumber));
        gainPower(p, new ThornsPower(p, this.magicNumber));
        gainPower(p, new VigorPower(p, this.magicNumber));
        gainPower(p, new IntangiblePlayerPower(p, this.magicNumber));
        gainPower(p, new ArtifactPower(p, this.magicNumber));
    }

}
