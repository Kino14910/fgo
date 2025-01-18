package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SarasvatiMeltoutAction extends AbstractGameAction {
    private final AbstractCreature c;

    public SarasvatiMeltoutAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
    }

    public void update() {
        for (AbstractPower p : this.c.powers) {
            if (p.type == AbstractPower.PowerType.BUFF
                    || p.ID.equals("Malleable")
                    || p.ID.equals("Plated Armor")
                    || p.ID.equals("Artifact")
                    || p.ID.equals("Metallicize")) {
                this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
            }
        }

        this.isDone = true;
    }
}
