package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.action.HeroicKingAction;
import fgo.action.KingToHandAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.HeroicKingPower;
import fgo.util.CardStats;

public class HeroicKing extends FGOCard {
    public static final String ID = makeID(HeroicKing.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    public HeroicKing() {
        super(ID, INFO);
        setDamage(5, 3);
        setMagic(2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeroicKing();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; ++i) {
            /*if (p.hasPower(HeroicKingPower.POWER_ID) && p.getPower(HeroicKingPower.POWER_ID).amount >= 12) {
                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
            } else {*/
                /*addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.1F));*/
                addToBot(new HeroicKingAction(this, AbstractGameAction.AttackEffect.NONE));
            //}
        }

        /*if (p.hasPower(HeroicKingPower.POWER_ID) && p.getPower(HeroicKingPower.POWER_ID).amount >= 12) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }

        addToBot(new ApplyPowerAction(p, p, new HeroicKingPower(p, 1), 1));*/
    }

    @Override
    public void tookDamage() {
        addToBot(new KingToHandAction(this, 1));
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(HeroicKingPower.POWER_ID)) {
            baseMagicNumber = (AbstractDungeon.player.getPower(HeroicKingPower.POWER_ID)).amount + 2;
            magicNumber = baseMagicNumber;
            /*if (AbstractDungeon.player.getPower(HeroicKingPower.POWER_ID).amount >= 12) {
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            }*/
        }
        super.applyPowers();
        initializeDescription();
    }
}
