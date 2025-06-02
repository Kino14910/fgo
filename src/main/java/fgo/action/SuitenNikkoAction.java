/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.megacrit.cardcrawl.actions.AbstractGameAction
 *  com.megacrit.cardcrawl.cards.AbstractCard
 *  com.megacrit.cardcrawl.core.Settings
 *  com.megacrit.cardcrawl.dungeons.AbstractDungeon
 */
package fgo.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SuitenNikkoAction
extends AbstractGameAction {
    public SuitenNikkoAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            c.setCostForTurn(-this.amount);
        }
        this.isDone = true;
    }
}

