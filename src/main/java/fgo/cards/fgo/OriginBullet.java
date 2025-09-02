package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.action.IgnoresInvincibilityAction;
import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.utils.CardStats;
import fgo.utils.Sounds;

public class OriginBullet extends FGOCard {
    public static final String ID = makeID(OriginBullet.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public OriginBullet() {
        super(ID, INFO);
        setExhaust(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int BulletAmt = 0;
        for (AbstractPower ignored : m.powers) {
            ++BulletAmt;
        }
        addToBot(new SFXAction(Sounds.gun));
        addToBot(new IgnoresInvincibilityAction(m, upgraded ? BulletAmt : 0));
    }
}
