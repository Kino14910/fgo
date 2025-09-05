package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.AntiPurgeDefensePower;

public class EternalMemories extends AbsNoblePhantasmCard {
    public static final String ID = makeID(EternalMemories.class.getSimpleName());

    public EternalMemories() {
        super(ID,CardType.POWER, CardTarget.SELF);
        setMagic(2, 1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new RemoveDebuffsAction(p));
        addToBot(new ApplyPowerAction(p, p, new AntiPurgeDefensePower(p, 1)));
    }
}
