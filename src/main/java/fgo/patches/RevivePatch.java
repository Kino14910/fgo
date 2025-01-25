package fgo.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import fgo.powers.*;
import fgo.relics.deprecated.CommandSpell;
import javassist.CtBehavior;

public class RevivePatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class hasEnoughEnergyPatcher {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<Void> Insert(AbstractPlayer p) {
            String[] powerIds = {
                GutsPower.POWER_ID,
                GutsAtTheWellPower.POWER_ID,
                ComeOnPower.POWER_ID,
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

            if (p.hasRelic(CommandSpell.ID) && p.getRelic(CommandSpell.ID).counter == 3) {
                p.currentHealth = 0;
                p.getRelic(CommandSpell.ID).onTrigger();

                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "isDead");
                return LineFinder.findInOrder(ctBehavior, fieldAccessMatcher);
            }
        }
    }
}
