package fgo.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fgo.patches.Enum.FGOCardColor;
import fgo.patches.Enum.CardTagsEnum;

public abstract class AbsNoblePhantasmCard extends FateMagineerCard {
    public AbsNoblePhantasmCard(String id, String name, String img, String rawDescription, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(id, name, img, -2, rawDescription, type, FGOCardColor.Noble_Phantasm, AbstractCard.CardRarity.SPECIAL, target);
        this.selfRetain = true;
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