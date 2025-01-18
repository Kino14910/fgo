package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import fgo.cards.FGOCard;
import fgo.cards.status.CurseDisaster;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class Canopus extends FGOCard {
    public static final String ID = makeID(Canopus.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    public Canopus() {
        super(ID, INFO);
        setDamage(13, 4);
        cardsToPreview = new CurseDisaster();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Canopus();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new HemokinesisEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        }

        addToBot(
                new DamageAction(
                        m,
                        new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY
                )
        );
        addToBot(new MakeTempCardInDiscardAction(cardsToPreview, 1));
    }
}
