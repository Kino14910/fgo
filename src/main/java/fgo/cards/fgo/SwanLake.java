package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.WatersidePower;
import fgo.util.CardStats;

public class SwanLake extends FGOCard {
    public static final String ID = makeID(SwanLake.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public SwanLake() {this(0);}
    
    public SwanLake(int upgrades) {
        super(ID, INFO);
        setDamage(2, 1);
        setMagic(3, 1);
        timesUpgraded = upgrades;
        setExhaust();
    }
    
    @Override
    public void upgrade() {
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

        if (upgradeDamage)
            this.upgradeDamage(damageUpgrade);

        if (upgradeMagic)
            this.upgradeMagicNumber(magicUpgrade);

        for (LocalVarInfo var : cardVariables.values()) {
            upgradeCustomVar(var);
        }

        if (baseExhaust ^ upgExhaust)
            this.exhaust = upgExhaust;

        this.initializeDescription();
    }
    @Override
    public boolean canUpgrade() {return true;}

    @Override
    public AbstractCard makeCopy() {
        return new SwanLake(timesUpgraded);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new ApplyPowerAction(p, p, new WatersidePower(p)));
    }
}
