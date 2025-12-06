package fgo.powers;

import static fgo.FGOMod.makeID;
import static fgo.utils.ModHelper.addToBotAbstract;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class SunlightPower extends BasePower {
    public static final String POWER_ID = makeID(SunlightPower.class.getSimpleName());
   private static final PowerType TYPE = PowerType.BUFF;

    public SunlightPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (this.owner.hasPower("Vigor")) {
                int VigorAmt = this.owner.getPower("Vigor").amount;
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, VigorAmt), VigorAmt));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBotAbstract(() -> {
            if (p.hasPower("Vigor")) {
                int strAmt = p.getPower("Vigor").amount;
                addToBot(new ApplyPowerAction(p, p, new VigorPower(p, strAmt), strAmt));
        }
        });
        addToBot(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    
}
