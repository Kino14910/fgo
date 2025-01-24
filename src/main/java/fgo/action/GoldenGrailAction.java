package fgo.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fgo.powers.BurnDamagePower;
import fgo.powers.SevenBeastCrownsPower;

public class GoldenGrailAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;
    private final AbstractPlayer p;
    private final int energyOnUse;
    public GoldenGrailAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, int amount) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.amount = amount;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }


        if (effect >= 0) {

            for (int i = 0; i < effect; i++) {
                addToBot(new AllEnemyApplyPowerAction(p, amount,
                        monster -> new BurnDamagePower(monster, amount)
                        )
                );
            }

                addToBot(new ApplyPowerAction(p, p, new SevenBeastCrownsPower(p, 7 - effect), 7 - effect));

            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }

        isDone = true;
    }
}
