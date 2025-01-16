package fgo.cards.colorless;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.BaseCard;
import fgo.patches.Enum.AbstractCardEnum;
import fgo.powers.WallOfSnowflakesPower;
import fgo.util.CardStats;

@AutoAdd.Ignore
public class WallOfSnowflakes extends BaseCard {
    public static final String ID = makeID(WallOfSnowflakes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            AbstractCardEnum.Master_COLOR,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1
    );
    public WallOfSnowflakes() {
        super(ID, info);
        setBlock(20);
        setMagic(15, 5);
        setCostUpgrade(2);
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new WallOfSnowflakes();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
        this.addToBot(new ApplyPowerAction(p, p, new WallOfSnowflakesPower(p, this.magicNumber), this.magicNumber));
    }
}
