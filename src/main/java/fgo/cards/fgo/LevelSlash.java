package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import fgo.cards.FGOCard;
import fgo.cards.colorless.CrimsonSlash;
import fgo.cards.colorless.DivineDualEdge;
public class LevelSlash extends FGOCard {
    public static final String ID = makeID(LevelSlash.class.getSimpleName());
    public LevelSlash() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY, CardRarity.RARE);
        setDamage(6, 3);
        setExhaust();
        
        MultiCardPreview.add(this, new CrimsonSlash(), new DivineDualEdge());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ChangeStanceAction("Calm"));
        addToBot(new MakeTempCardInDrawPileAction(new DivineDualEdge().makeStatEquivalentCopy(), 1, true, true));
    }
}


