package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

import fgo.cards.FGOCard;
public class PrimevalRune extends FGOCard {
    public static final String ID = makeID(PrimevalRune.class.getSimpleName());
    public PrimevalRune() {
        super(ID, 1, CardType.SKILL, CardTarget.ALL_ENEMY, CardRarity.RARE, CardColor.COLORLESS);
        setMagic(2, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new BlizzardEffect(5, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            addToBot(new VFXAction(new BlizzardEffect(5, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}


