package fgo.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;

public abstract class AbsNoblePhantasmCard extends FateMagineerCard {
    public AbsNoblePhantasmCard(String id, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(id, -2, type, target, AbstractCard.CardRarity.SPECIAL, FGOCardColor.Noble_Phantasm);
        setSelfRetain();
        this.tags.add(CardTagsEnum.Noble_Phantasm);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void triggerWhenDrawn() {
        if (this.inBottleFlame || this.inBottleLightning || this.inBottleTornado) {
            this.addToTop(new DrawCardAction(AbstractDungeon.player, 2));
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }
}