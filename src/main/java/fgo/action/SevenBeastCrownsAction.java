package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fgo.powers.SevenBeastCrownsPower;

public class SevenBeastCrownsAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;
    private final AbstractPlayer p;
    private final int energyOnUse;
    public SevenBeastCrownsAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect >= 0) {
            if (effect >= 7) {
                this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 3), 3));
                this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 4), 4));
                this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, 4), 4));
                this.addToBot(new ApplyPowerAction(p, p, new BerserkPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new DrawPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 2), 2));
            } else {
                this.addToBot(new ApplyPowerAction(p, p, new SevenBeastCrownsPower(p, 7 - effect), 7 - effect));
            }
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
