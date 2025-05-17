package fgo.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.characters.Master;
import fgo.powers.StarPower;
import fgo.powers.StarRatePower;
import javassist.CtBehavior;

import static fgo.util.GeneralUtils.addToBot;

@SpirePatch(
    clz = AbstractMonster.class,
    method = "damage"
)
public class CriticalStarPatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractMonster __instance, DamageInfo info) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p instanceof Master && info.type == DamageInfo.DamageType.NORMAL) {
            addToBot(new ApplyPowerAction(p, p, new StarPower(p, 1 + ( p.hasPower(StarRatePower.POWER_ID) ? p.getPower(StarRatePower.POWER_ID).amount : 0 ) )));
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
