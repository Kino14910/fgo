package fgo.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fgo.characters.Master;
import fgo.powers.NPRatePower;
import fgo.powers.SealNPPower;

public class PatchCostAmtKey {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString((String)"fgo:NPText").TEXT;

    @SpirePatch(clz=AbstractCard.class, method="renderCardTip")
    public static class PatchSkeletonKeyCanUpgrade {
        @SpirePrefixPatch
        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (!(AbstractDungeon.player instanceof Master) || __instance.isLocked || AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode) {
                return;
            }
            if (PatchSkeletonKeyCanUpgrade.isInCombat() && AbstractDungeon.player.hoveredCard == __instance && __instance.costForTurn > -2 && __instance.costForTurn != 0 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
                int costModifier = PatchSkeletonKeyCanUpgrade.getCostModifier();
                int costAmt = PatchSkeletonKeyCanUpgrade.calculateCostAmount(__instance.costForTurn, costModifier);
                FontHelper.renderFontCentered((SpriteBatch)sb, (BitmapFont)FontHelper.topPanelInfoFont, (String)("+" + costAmt + "% " + NPTEXT[0]), (float)__instance.hb.cX, (float)(__instance.hb.height + 24.0f * Settings.scale), (Color)Color.WHITE.cpy());
            }
        }

        private static boolean isInCombat() {
            return AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
        }

        private static int getCostModifier() {
            boolean hasGoldLaw = AbstractDungeon.player.hasPower(NPRatePower.POWER_ID);
            if (Master.fgoNp >= 200) {
                return hasGoldLaw ? 20 : 10;
            }
            if (Master.fgoNp >= 100) {
                return hasGoldLaw ? 14 : 7;
            }
            return hasGoldLaw ? 10 : 5;
        }

        private static int calculateCostAmount(int costForTurn, int costModifier) {
            return costForTurn == -1 ? EnergyPanel.totalCount * costModifier : costForTurn * costModifier;
        }
    }
}

