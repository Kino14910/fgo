package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.StarPower;
import fgo.util.CardStats;

public class CharismaOfTheJade extends FGOCard {
    public static final String ID = makeID(CharismaOfTheJade.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public CharismaOfTheJade() {
        super(ID, INFO);
        setMagic(2, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, -10)));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
    }
    
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } 
        
        if (!p.hasPower(StarPower.POWER_ID) || p.getPower(StarPower.POWER_ID).amount < 10) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        
        return canUse;
    }

    @Override
    public void triggerOnGlowCheck() {
        if(!AbstractDungeon.player.hasPower(StarPower.POWER_ID))
            return;
        glowColor = AbstractDungeon.player.getPower(StarPower.POWER_ID).amount < 10 
                    ? AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
                    : AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }
}
