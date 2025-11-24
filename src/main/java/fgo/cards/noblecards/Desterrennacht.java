package fgo.cards.noblecards;

import static fgo.characters.CustomEnums.Foreigner;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.StarRegenPower;
import fgo.powers.TerrorPower;
public class Desterrennacht extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Desterrennacht.class.getSimpleName());

    public Desterrennacht() {
        super(ID,CardType.POWER, CardTarget.ALL_ENEMY);
        setMagic(3, 2);
        tags.add(Foreigner);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new TerrorPower(mo, 60, 3)));
        }
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50)));
        addToBot(new ApplyPowerAction(p, p, new StarRegenPower(p, 10)));
    }
}
