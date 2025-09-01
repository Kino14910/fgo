package fgo.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
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
        PurgeField.purge.set(this, true);
        CommonKeywordIconsField.useIcons.set(this, false);
        tags.add(CardTagsEnum.Noble_Phantasm);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void triggerWhenDrawn() {
        if (inBottleFlame || inBottleLightning || inBottleTornado) {
            addToTop(new DrawCardAction(AbstractDungeon.player, 2));
            addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

}