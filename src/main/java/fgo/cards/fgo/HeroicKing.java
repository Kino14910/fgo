package fgo.cards.fgo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.HeroicKingPower;
public class HeroicKing extends FGOCard {
    public static final String ID = makeID(HeroicKing.class.getSimpleName());
    public HeroicKing() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY, CardRarity.UNCOMMON);
        setDamage(5, 3);
        setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this));
        }
    }

    @Override
    public void tookDamage() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new FetchAction(p.discardPile, card -> card == this, 1));
        addToBot(new ApplyPowerAction(p, p, new HeroicKingPower(p, 1)));
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(HeroicKingPower.POWER_ID)) {
            baseMagicNumber = (AbstractDungeon.player.getPower(HeroicKingPower.POWER_ID)).amount + 2;
            magicNumber = baseMagicNumber;
        }
        super.applyPowers();
        initializeDescription();
    }
}


