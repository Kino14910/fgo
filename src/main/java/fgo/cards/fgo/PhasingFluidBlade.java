package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fgo.cards.FGOCard;
import fgo.cards.optionCards.PhasingFluidBladeATTACK;
import fgo.cards.optionCards.PhasingFluidBladeSKILL;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

import java.util.ArrayList;

public class PhasingFluidBlade extends FGOCard {
    public static final String ID = makeID(PhasingFluidBlade.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    public PhasingFluidBlade() {
        super(ID, INFO);
        setDamage(7, 3);
        setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));

        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new PhasingFluidBladeATTACK());
        stanceChoices.add(new PhasingFluidBladeSKILL());
        addToBot(new ChooseOneAction(stanceChoices));
    }
}
