package fgo.cards.noblecards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WingBoots;

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
        if (!p.hasRelic(WingBoots.ID)) {
            spawnRelicWingBoots();
            return;
        }

        AbstractRelic relic = p.getRelic(WingBoots.ID);
        if (relic != null && relic.counter == 0) {
            p.loseRelic(WingBoots.ID);
            spawnRelicWingBoots();
        }
    }


    private void spawnRelicWingBoots() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new WingBoots());
    }
}
