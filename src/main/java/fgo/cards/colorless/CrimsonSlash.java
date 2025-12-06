package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import fgo.cards.FGOCard;
public class CrimsonSlash extends FGOCard {
    public static final String ID = makeID(CrimsonSlash.class.getSimpleName());
    public CrimsonSlash() {
        super(ID, 2, CardType.ATTACK, CardTarget.ALL_ENEMY, CardRarity.SPECIAL, CardColor.COLORLESS);
        setDamage(8, 2);
        setMagic(2, 1);
        setExhaust();
        cardsToPreview = new DivineDualEdge();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new ChangeStanceAction("Wrath"));
        addToBot(new MakeTempCardInDrawPileAction(cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
    }
}


