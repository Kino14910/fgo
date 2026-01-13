package fgo.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import fgo.powers.interfaces.onEnemyLoseHpPower;
import fgo.relics.interfaces.onEnemyLoseHpRelic;

@SpirePatch(
        clz=AbstractMonster.class,
        method="damage"
)
public class EnemyLoseHpPatch
{
    public static SpireReturn<Integer> Postfix(AbstractMonster __instance, DamageInfo info)
    {
        if (info.output > __instance.currentBlock) {
            for (AbstractPower power : __instance.powers) {
                if (power instanceof onEnemyLoseHpPower) {
                    ((onEnemyLoseHpPower) power).onEnemyLoseHp(info);
                }
            }
            if (__instance.isPlayer) {
                for (AbstractRelic relic : AbstractDungeon.player.relics) {
                    if (relic instanceof onEnemyLoseHpRelic) {
                        ((onEnemyLoseHpRelic) relic).onEnemyLoseHp(info);
                    }
                }
            }
        }

        return SpireReturn.Continue();
    }
}