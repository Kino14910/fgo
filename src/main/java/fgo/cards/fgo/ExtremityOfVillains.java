package fgo.cards.fgo;

import static fgo.characters.CustomEnums.FGO_Foreigner;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.powers.StarPower;

public class ExtremityOfVillains extends FGOCard {
    public static final String ID = makeID(ExtremityOfVillains.class.getSimpleName());
    public ExtremityOfVillains() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY, CardRarity.UNCOMMON);
        setDamage(8, 4);
        setMagic(2);
        tags.add(FGO_Foreigner);
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if ((p.hasPower(StarPower.POWER_ID)) && p.getPower(StarPower.POWER_ID).amount >= 10) {
            addToTop(new DrawCardAction(AbstractDungeon.player, magicNumber));
            addToTop(new GainEnergyAction(1));
        }
        addToBot(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}


