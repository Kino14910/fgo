package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import fgo.action.IgnoresInvincibilityAction;
import fgo.cards.FGOCard;
import fgo.utils.Sounds;

public class OriginBullet extends FGOCard {
    public static final String ID = makeID(OriginBullet.class.getSimpleName());
    public OriginBullet() {
        super(ID, 1, CardType.SKILL, CardTarget.ENEMY, CardRarity.UNCOMMON);
        setExhaust(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int BulletAmt = 0;
        for (AbstractPower ignored : m.powers) {
            ++BulletAmt;
        }
        addToBot(new SFXAction(Sounds.gun));
        addToBot(new IgnoresInvincibilityAction(m, true));
    }
}


