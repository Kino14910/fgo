package fgo.cards.fgo;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.SongOfThePoetAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class SongOfThePoet extends FGOCard {
    public static final String ID = makeID(SongOfThePoet.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public SongOfThePoet() {
        super(ID, INFO);
        setDamage(3, 1);
        setMagic(3);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SongOfThePoetAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        addToBot(new SongOfThePoetAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        addToBot(new SongOfThePoetAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
    }
}
