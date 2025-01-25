package fgo.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.characters.Master;
import fgo.powers.StarGainPower;
import javassist.CtBehavior;

@SpirePatch(
    clz = AbstractMonster.class,
    method = "damage"
)
public class CriticalStarPatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractMonster __instance, DamageInfo info) {
        if(AbstractDungeon.player instanceof Master){
            if(info.type == DamageInfo.DamageType.NORMAL){
                AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            AbstractDungeon.player,
                            AbstractDungeon.player,
                            new StarGainPower(AbstractDungeon.player, 1), 1)
                );
            }
        }

    }


    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher matcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "currentHealth");
            int line = LineFinder.findAllInOrder(ctBehavior, matcher)[2] + 1;
            return new int[]{line};
        }

    }
}
