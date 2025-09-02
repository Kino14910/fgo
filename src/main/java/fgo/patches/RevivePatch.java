package fgo.patches;

import static fgo.utils.ModHelper.addToBot;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import fgo.action.FgoNpAction;
import fgo.powers.DeathOfDeathPower;
import fgo.powers.GutsAtTheWellPower;
import fgo.powers.GutsPower;
import fgo.powers.SpringOfFirePower;
import fgo.ui.panels.CommandSpellPanel;
import javassist.CtBehavior;
import static fgo.characters.CustomEnums.FGO_MASTER;
public class RevivePatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class hasEnoughEnergyPatcher {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<Void> Insert(AbstractPlayer p) {
            String[] powerIds = {
                GutsPower.POWER_ID,
                GutsAtTheWellPower.POWER_ID,
                SpringOfFirePower.POWER_ID,
                DeathOfDeathPower.POWER_ID
            };

            if (p.hasPower(GutsPower.POWER_ID)) {
                p.currentHealth = 0;
                for (String id : powerIds) {
                    if (p.hasPower(id)) {
                        p.getPower(id).onSpecificTrigger();
                    }
                }
                return SpireReturn.Return(null);
            }

            if (CommandSpellPanel.commandSpellCount == 3
                    && AbstractDungeon.currMapNode != null
                    && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
                    && AbstractDungeon.player.chosenClass == FGO_MASTER
                    ) {
                CommandSpellPanel.commandSpellCount = 0;
                p.heal(p.maxHealth, true);
                addToBot(new FgoNpAction(300));
                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "isDead");
                return LineFinder.findInOrder(ctBehavior, fieldAccessMatcher);
            }
        }
    }
}
